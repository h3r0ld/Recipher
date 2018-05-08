package hu.herold.mobsoft.recipher.ui.recipes;

/**
 * Created by herold on 2018. 05. 08..
 */

public class RecipesAdapterOptions {
    private boolean IsFavouriteVisible = true;

    private Class<?> targetNavigationClass;

    public boolean isFavouriteVisible() {
        return IsFavouriteVisible;
    }

    public void setFavouriteVisible(boolean favouriteVisible) {
        IsFavouriteVisible = favouriteVisible;
    }

    public Class<?> getTargetNavigationClass() {
        return targetNavigationClass;
    }

    public void setTargetNavigationClass(Class<?> targetNavigationClass) {
        this.targetNavigationClass = targetNavigationClass;
    }
}
