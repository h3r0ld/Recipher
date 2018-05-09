package hu.herold.mobsoft.recipher;

import javax.inject.Singleton;

import dagger.Component;
import hu.herold.mobsoft.recipher.db.MockDbModule;
import hu.herold.mobsoft.recipher.interactor.InteractorModule;
import hu.herold.mobsoft.recipher.network.MockNetworkModule;

/**
 * Created by herold on 2018. 05. 09..
 */

@Singleton
@Component(modules = {MockDbModule.class, MockNetworkModule.class, TestModule.class, InteractorModule.class})
public interface TestComponent extends RecipherApplicationComponent {
}
