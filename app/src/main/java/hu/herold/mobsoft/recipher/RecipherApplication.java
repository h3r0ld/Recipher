package hu.herold.mobsoft.recipher;

import android.app.Application;
import android.content.Context;

import hu.herold.mobsoft.recipher.db.DbModule;
import hu.herold.mobsoft.recipher.ui.UIModule;

/**
 * Created by herold on 2018. 03. 23..
 */

public class RecipherApplication extends Application {

    public static RecipherApplicationComponent injector;

    @Override
    public void onCreate() {
        super.onCreate();

        injector = DaggerRecipherApplicationComponent.builder()
                .uIModule(new UIModule(this))
                .dbModule(new DbModule(this))
                .build();
    }
}
