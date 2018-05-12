package hu.herold.mobsoft.recipher.ui.favourites.password;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.analytics.HitBuilders;
import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsActivity;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsScreen;
import hu.herold.mobsoft.recipher.ui.recipes.RecipesAdapter;

import static hu.herold.mobsoft.recipher.ui.recipes.RecipesAdapter.RECIPE;

public class PasswordProtectedActivity extends AppCompatActivity implements PasswordProtectedScreen{

    @Inject
    PasswordProtectedPresenter passwordProtectedPresenter;

    @BindView(R.id.recipherImage)
    ImageView recipherImage;
    @BindView(R.id.passwordEditText)
    EditText passwordEditText;
    @BindView(R.id.exitButton)
    Button exitButton;
    @BindView(R.id.unlockButton)
    Button unlockButton;

    private Recipe recipe;
    private String password;

    public PasswordProtectedActivity() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_protected);
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null && intent.getExtras().containsKey(RECIPE)) {
            String json = intent.getExtras().getString(RECIPE);
            recipe = (new Gson()).fromJson(json, Recipe.class);
        } else {
            showMessage("No data found.");
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        passwordProtectedPresenter.attachScreen(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        passwordProtectedPresenter.detachScreen();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.exitButton)
    public void onExitButtonClicked() {
        finish();
    }

    @OnClick(R.id.unlockButton)
    public void onUnlockButtonClicked() {
        password = passwordEditText.getText().toString();
        passwordProtectedPresenter.validatePassword(recipe, password);
    }

    @Override
    public void showValidationFailed(String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        final TextView textView = new TextView(this);


        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(lp);

        alertDialogBuilder.setTitle(R.string.valiationFailedTitle);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(true)
                .setNeutralButton(R.string.exit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setView(textView);
        alertDialog.show();
    }

    @Override
    public void navigateToFavouriteRecipe() {
        Intent intent = new Intent(this, FavouriteDetailsActivity.class);
        intent.putExtra(RECIPE, (new Gson()).toJson(recipe));
        startActivity(intent);
    }
}
