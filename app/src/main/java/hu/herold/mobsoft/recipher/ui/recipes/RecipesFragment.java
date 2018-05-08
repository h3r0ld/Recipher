package hu.herold.mobsoft.recipher.ui.recipes;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import hu.herold.mobsoft.recipher.R;
import hu.herold.mobsoft.recipher.RecipherApplication;
import hu.herold.mobsoft.recipher.network.model.Recipe;
import hu.herold.mobsoft.recipher.ui.ViewPagerFragment;
import hu.herold.mobsoft.recipher.ui.recipes.details.RecipeDetailsActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecipesFragment extends Fragment implements RecipesScreen, ViewPagerFragment {

    @Inject
    RecipesPresenter recipesPresenter;

    @BindView(R.id.recipeRecyclerView)
    RecyclerView recipeRecyclerView;
    @BindView(R.id.emptyTextView)
    TextView emptyTextView;
    @BindView(R.id.recipeSwipeRefreshLayout)
    SwipeRefreshLayout recipeSwipeRefreshLayout;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.searchView)
    SearchView searchView;

    Unbinder unbinder;

    private String recipeFilter;
    private List<Recipe> recipeList;
    private RecipesAdapter recipesAdapter;


    public RecipesFragment() {
        RecipherApplication.injector.inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recipeRecyclerView.setLayoutManager(llm);

        recipeList = new ArrayList();

        RecipesAdapterOptions recipesAdapterOptions = new RecipesAdapterOptions();
        recipesAdapterOptions.setTargetNavigationClass(RecipeDetailsActivity.class);

        recipesAdapter = new RecipesAdapter(getActivity(), recipeList, recipesAdapterOptions);
        recipeRecyclerView.setAdapter(recipesAdapter);

        recipeSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recipeFilter = searchView.getQuery().toString();
                recipesPresenter.refreshRecipes(recipeFilter);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recipeFilter = query;
                recipesPresenter.refreshRecipes(recipeFilter);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recipeFilter = newText;
                return false;
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        recipesPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        recipesPresenter.detachScreen();
    }

    @Override
    public void onResume() {
        super.onResume();
        recipeFilter = searchView.getQuery().toString();
        progressBar.setVisibility(View.VISIBLE);
        recipesPresenter.refreshRecipes(recipeFilter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showRecipes(List<Recipe> recipes) {
        if (recipeSwipeRefreshLayout != null) {
            recipeSwipeRefreshLayout.setRefreshing(false);
        }

        recipeList.clear();
        recipeList.addAll(recipes);
        recipesAdapter.notifyDataSetChanged();

        progressBar.setVisibility(View.GONE);

        if (recipeList.isEmpty()) {
            recipeRecyclerView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            recipeRecyclerView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showNetworkError(String errorMsg) {
        if (recipeSwipeRefreshLayout != null) {
            recipeSwipeRefreshLayout.setRefreshing(false);
        }

        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSwitchedTo() {
        recipeFilter = searchView.getQuery().toString();
        progressBar.setVisibility(View.VISIBLE);
        recipesPresenter.refreshRecipes(recipeFilter);
    }
}
