package hu.herold.mobsoft.recipher.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;
import hu.herold.mobsoft.recipher.network.mock.MockRecipeApi;

/**
 * Created by herold on 2018. 04. 24..
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public ApiClient provideApiClient() {
        return new ApiClient("bd3b92cf03a07b31ffd2fc927e71ca71");
    }

    @Provides
    @Singleton
    public RecipeApi provideRecipeApi(ApiClient apiClient) {
        return apiClient.createService(RecipeApi.class);
        //return new MockRecipeApi();
    }
}
