package hu.herold.mobsoft.recipher.ui.recipes;

import android.content.Context;
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

/**
 * Created by herold on 2018. 05. 06..
 */

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.ViewHolder> {

    private Context context;
    private List<Recipe> recipeList;

    public RecipesAdapter(Context context, List<Recipe> recipeList) {
        this.context = context;
        this.recipeList = recipeList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_recipe, viewGroup, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);

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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
