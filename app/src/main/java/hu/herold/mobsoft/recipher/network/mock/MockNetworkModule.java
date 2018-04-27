package hu.herold.mobsoft.recipher.network.mock;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import hu.herold.mobsoft.recipher.network.ApiClient;
import hu.herold.mobsoft.recipher.network.api.RecipeApi;

/**
 * Created by herold on 2018. 04. 26..
 */

@Module
public class MockNetworkModule {

    @Provides
    @Singleton
    public RecipeApi provideRecipeApi(ApiClient apiClient) {
        return new MockRecipeApi();
    }
}
