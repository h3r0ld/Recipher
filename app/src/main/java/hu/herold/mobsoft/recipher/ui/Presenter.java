package hu.herold.mobsoft.recipher.ui;

/**
 * Created by herold on 2018. 03. 23..
 */

public abstract class Presenter<T> {
    protected T screen;


    public void attachScreen(T screen)
    {
        this.screen = screen;
    }

    public  void detachScreen()
    {
        this.screen = null;
    }
}
