package hu.herold.mobsoft.recipher;

import javax.inject.Singleton;

import dagger.Component;
import hu.herold.mobsoft.recipher.interactor.InteractorModule;
import hu.herold.mobsoft.recipher.ui.UIModule;
import hu.herold.mobsoft.recipher.ui.main.MainActivity;

/**
 * Created by herold on 2018. 03. 23..
 */

@Singleton
@Component(modules = {UIModule.class, InteractorModule.class})
public interface RecipherApplicationComponent {
    void inject(MainActivity mainActivity);
}
