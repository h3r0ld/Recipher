package hu.herold.mobsoft.recipher.ui.favourites.password;

/**
 * Created by herold on 2018. 05. 12..
 */

public interface PasswordProtectedScreen {

    void showValidationFailed(String message);

    void navigateToFavouriteRecipe();

    void showMessage(String message);
}
