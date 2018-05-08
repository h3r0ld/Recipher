package hu.herold.mobsoft.recipher.ui.recipes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsActivity;

/**
 * Created by herold on 2018. 05. 06..
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    public static final String RECIPE_ID = "RECIPE_ID";

    private Context context;
    private List<Recipe> recipeList;

    public RecipesAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_recipe, viewGroup, false);
        return new ViewHolder(itemView, context);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

        holder.setRecipe(recipe);

        if (recipe.getImageUrl() != null) {
            Glide.with(context).load(recipe.getImageUrl()).into(holder.imageIV);
        }

        holder.nameTV.setText(recipe.getTitle());
        holder.scoreTV.setText(recipe.getSocialRank().toString());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.cardRecipe)
        CardView cardRecipe;
        @BindView(R.id.imageIV)
        ImageView imageIV;
        @BindView(R.id.nameTV)
        TextView nameTV;
        @BindView(R.id.scoreTV)
        TextView scoreTV;

        private Recipe recipe;

        public ViewHolder(View itemView, final Context context) {
            super(itemView);
            ButterKnife.bind(this, itemView);

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

                    Intent intent = new Intent(context, RecipeDetailsActivity.class);
                    intent.putExtra(RECIPE_ID, recipe.getRecipeId());

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
