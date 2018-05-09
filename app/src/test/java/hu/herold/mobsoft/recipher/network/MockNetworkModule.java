package hu.herold.mobsoft.recipher.network;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;

/**
 * Created by herold on 2018. 04. 26..
 */

@Module
public class MockNetworkModule {

    @Provides
    @Singleton
    public ApiClient provideApiClient() {
        return new ApiClient("");
    }

    @Provides
    @Singleton
    public RecipeApi provideRecipeApi(ApiClient apiClient) {
        return new MockRecipeApi();
    }
}
