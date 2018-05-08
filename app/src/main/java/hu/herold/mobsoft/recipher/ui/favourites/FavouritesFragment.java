package hu.herold.mobsoft.recipher.ui.favourites;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
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
import hu.herold.mobsoft.recipher.ui.recipes.RecipesAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends Fragment implements FavouritesScreen, ViewPagerFragment {

    @Inject
    FavouritesPresenter favouritesPresenter;

    @BindView(R.id.titleFilterEditText)
    EditText titleFilterEditText;
    @BindView(R.id.favouritesRecyclerView)
    RecyclerView favouritesRecyclerView;
    @BindView(R.id.emptyTextView)
    TextView emptyTextView;
    @BindView(R.id.favouritesSwipeRefreshLayout)
    SwipeRefreshLayout favouritesSwipeRefreshLayout;
    @BindView(R.id.favouritesFrameLayout)
    FrameLayout favouritesFrameLayout;

    Unbinder unbinder;

    private String titleFilter;
    private List<String> ingredientsFilter;
    private List<Recipe> recipeList;
    private RecipesAdapter recipesAdapter;

    public FavouritesFragment() {
        RecipherApplication.injector.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        favouritesRecyclerView.setLayoutManager(llm);

        recipeList = new ArrayList();

        recipesAdapter = new RecipesAdapter(getActivity(), recipeList);
        favouritesRecyclerView.setAdapter(recipesAdapter);

        favouritesSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                titleFilter = titleFilterEditText.getText().toString();
                favouritesPresenter.refreshFavouriteRecipes(titleFilter, ingredientsFilter);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        favouritesPresenter.attachScreen(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        favouritesPresenter.detachScreen();
    }

    @Override
    public void onResume() {
        super.onResume();
        titleFilter = titleFilterEditText.getText().toString();
        favouritesPresenter.refreshFavouriteRecipes(titleFilter, ingredientsFilter);
    }

    @Override
    public void showFavouriteRecipes(List<Recipe> recipes) {
        if (favouritesSwipeRefreshLayout != null) {
            favouritesSwipeRefreshLayout.setRefreshing(false);
        }

        recipeList.clear();
        recipeList.addAll(recipes);
        recipesAdapter.notifyDataSetChanged();

        if (recipeList.isEmpty()) {
            favouritesRecyclerView.setVisibility(View.GONE);
            emptyTextView.setVisibility(View.VISIBLE);
        } else {
            favouritesRecyclerView.setVisibility(View.VISIBLE);
            emptyTextView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String message) {
        if (favouritesSwipeRefreshLayout != null) {
            favouritesSwipeRefreshLayout.setRefreshing(false);
        }
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onSwitchedTo() {
        titleFilter = titleFilterEditText.getText().toString();
        favouritesPresenter.refreshFavouriteRecipes(titleFilter, ingredientsFilter);
    }
}

