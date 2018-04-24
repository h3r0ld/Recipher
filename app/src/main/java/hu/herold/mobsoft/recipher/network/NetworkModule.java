package hu.herold.mobsoft.recipher.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;

/**
 * Created by herold on 2018. 04. 24..
 */

@Module
public class NetworkModule {

    private static final String FOOD2FORK_API_KEY = "FOOD2FORK_API_KEY";

    @Provides
    @Singleton
    public ApiClient provideApiClient() {
        return new ApiClient(System.getenv(FOOD2FORK_API_KEY));
    }

    @Provides
    @Singleton
    public RecipeApi provideRecipeApi(ApiClient apiClient) {
        return apiClient.createService(RecipeApi.class);
    }
}
