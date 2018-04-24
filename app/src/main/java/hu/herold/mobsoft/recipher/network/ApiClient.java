package hu.herold.mobsoft.recipher.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import hu.herold.mobsoft.recipher.network.auth.ApiKeyAuth;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

public class ApiClient {

    private OkHttpClient okClient;
    private Retrofit.Builder adapterBuilder;

    /**
     * Helper constructor for single api key
     * @param apiKey
     */
    public ApiClient(String apiKey) {
        this.addApiKeyAuthorization(apiKey);
        createDefaultAdapter();

    }
    
   public void createDefaultAdapter() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                .create();

       if (okClient == null) {
           okClient = new OkHttpClient();
       }
        
        String baseUrl = "http://food2fork.com/api";
        if(!baseUrl.endsWith("/"))
        	baseUrl = baseUrl + "/";

        adapterBuilder = new Retrofit
                .Builder()
                .baseUrl(baseUrl)
                .client(okClient)
                
                .addConverterFactory(GsonCustomConverterFactory.create(gson));
    }

    public <S> S createService(Class<S> serviceClass) {
        return adapterBuilder.build().create(serviceClass);
        
    }

    /**
     * Helper method to configure the first api key found
     * @param apiKey
     */
    private void addApiKeyAuthorization(String apiKey) {
        ApiKeyAuth keyAuth = new ApiKeyAuth("query", "key");;
        keyAuth.setApiKey(apiKey);
        if (okClient == null)
        {
            okClient = new OkHttpClient();
        }
        okClient = okClient.newBuilder().addInterceptor(keyAuth).build();
    }

    public Retrofit.Builder getAdapterBuilder() {
        return adapterBuilder;
    }

    public void setAdapterBuilder(Retrofit.Builder adapterBuilder) {
        this.adapterBuilder = adapterBuilder;
    }

    public OkHttpClient getOkClient() {
        return okClient;
    }
}

/**
 * This wrapper is to take care of this case:
 * when the deserialization fails due to JsonParseException and the
 * expected type is String, then just return the body string.
 */
class GsonResponseBodyConverterToString<T> implements Converter<ResponseBody, T> {
	  private final Gson gson;
	  private final Type type;

	  GsonResponseBodyConverterToString(Gson gson, Type type) {
	    this.gson = gson;
	    this.type = type;
	  }

	  @Override public T convert(ResponseBody value) throws IOException {
	    String returned = value.string();
	    try {
	      return gson.fromJson(returned, type);
	    } 
	    catch (JsonParseException e) {
                return (T) returned;
        } 
	 }
}

class GsonCustomConverterFactory extends Converter.Factory 
{
	public static GsonCustomConverterFactory create(Gson gson) {
	    return new GsonCustomConverterFactory(gson);
	  }

	  private final Gson gson;
	  private final GsonConverterFactory gsonConverterFactory;

	  private GsonCustomConverterFactory(Gson gson) {
	    if (gson == null) throw new NullPointerException("gson == null");
	    this.gson = gson;
	    this.gsonConverterFactory = GsonConverterFactory.create(gson);
	  }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if(type.equals(String.class))
            return new GsonResponseBodyConverterToString<Object>(gson, type);
        else
            return gsonConverterFactory.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return gsonConverterFactory.requestBodyConverter(type, annotations, retrofit);
    }
}

