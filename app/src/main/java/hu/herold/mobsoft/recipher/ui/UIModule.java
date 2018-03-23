package hu.herold.mobsoft.recipher.ui;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by herold on 2018. 03. 23..
 */

@Module
public class UIModule {
    private Context context;

    public UIModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }

//    @Provides
//    @Singleton
//    public MainPresenter provideMainPresenter() {
//        return new MainPresenter();
//    }
}
