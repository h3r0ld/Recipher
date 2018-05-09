package hu.herold.mobsoft.recipher;

import org.robolectric.RuntimeEnvironment;
import org.robolectric.shadows.ShadowLog;

import hu.herold.mobsoft.recipher.db.MockDbModule;

/**
 * Created by herold on 2018. 05. 09..
 */

public class TestHelper {

    public static void setTestInjector() {
        ShadowLog.stream = System.out;
        RecipherApplication application = (RecipherApplication) RuntimeEnvironment.application;
        RecipherApplicationComponent injector = DaggerTestComponent.builder().testModule(new TestModule(application.getApplicationContext())).mockDbModule(new MockDbModule(application.getApplicationContext())).build();
        application.injector = injector;
    }
}
