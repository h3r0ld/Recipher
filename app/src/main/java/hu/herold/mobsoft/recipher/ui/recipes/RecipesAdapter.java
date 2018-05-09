package hu.herold.mobsoft.recipher.ui.recipes;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.favourites.details.FavouriteDetailsActivity;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsActivity;

/**
 * Created by herold on 2018. 05. 06..
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public static final String RECIPE = "RECIPE";

    private Context context;
    private List<Recipe> recipeList;
    private RecipesAdapterOptions recipesAdapterOptions;

    public RecipesAdapter(Context context, List<Recipe> recipeList, RecipesAdapterOptions recipesAdapterOptions) {
        this.context = context;
        this.recipeList = recipeList;
        this.recipesAdapterOptions = recipesAdapterOptions;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_recipe, viewGroup, false);
        return new ViewHolder(itemView, context, recipesAdapterOptions);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        holder.setRecipe(recipe);

        if (recipe.getImageUrl() != null) {
            Glide.with(context).load(recipe.getImageUrl()).into(holder.pictImageView);
        }

        holder.titleTextView.setText(recipe.getTitle());
        holder.scoreTextView.setText(recipe.getSocialRank().toString());

        Drawable icon = null;

        if (recipe.getFavourite() != null && recipe.getFavourite()) {
            icon = context.getResources().getDrawable(R.drawable.ic_star_white_18dp);
        } else {
            icon = context.getResources().getDrawable(R.drawable.ic_star_border_black_18dp);
        }

        holder.favouriteIconImageView.setImageDrawable(icon);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.pictImageView)
        ImageView pictImageView;
        @BindView(R.id.titleTextView)
        TextView titleTextView;
        @BindView(R.id.socialScore)
        TextView socialScore;
        @BindView(R.id.scoreTextView)
        TextView scoreTextView;
        @BindView(R.id.favouriteIconImageView)
        ImageView favouriteIconImageView;
        @BindView(R.id.cardRecipe)
        CardView cardRecipe;

        private Recipe recipe;

        public ViewHolder(View itemView, final Context context, final RecipesAdapterOptions recipesAdapterOptions) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            if (!recipesAdapterOptions.isFavouriteVisible()) {
                favouriteIconImageView.setVisibility(View.INVISIBLE);
            }

            cardRecipe.setOnClickListener(new CardView.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
//                    Bundle bundle = new Bundle();
//                    bundle.putString(RECIPE_ID, recipe.getRecipeId());
//                    Fragment fragment = new RecipeDetailsFragment();
//                    fragment.setArguments(bundle);
//                    FragmentTransaction fmTransaction = fm.beginTransaction();
//                    fmTransaction.replace(R.id.recipesFrameLayout, fragment);
//                    fmTransaction.addToBackStack(null);
//                    fmTransaction.commit();

                    Intent intent = new Intent();
                    if (recipe.getFavourite() == null || recipe.getFavourite()) {
                        intent = new Intent(context, FavouriteDetailsActivity.class);
                    } else {
                        intent = new Intent(context, RecipeDetailsActivity.class);
                    }
                    //Intent intent = new Intent(context, recipesAdapterOptions.getTargetNavigationClass());
                    intent.putExtra(RECIPE, (new Gson()).toJson(recipe));

                    context.startActivity(intent);
                }
            });
        }

        public Recipe getRecipe() {
            return recipe;
        }

        public void setRecipe(Recipe recipe) {
            this.recipe = recipe;
        }
    }
}
