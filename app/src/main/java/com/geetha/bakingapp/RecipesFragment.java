package com.geetha.bakingapp;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.models.Step;

import org.parceler.Parcels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment implements RecipesAdapter.RecipeClickCallback {
    RecyclerView mRecipesRv;
    RecipesAdapter recipesAdapter;
    List <Recipe> mRecipesList = new ArrayList <> ();
    private RecipesFragmentViewModel recipesFragmentViewModel;

    Observer <List <Recipe>> recipeObserver = new Observer <List <Recipe>> () {
        @Override
        public void onChanged(List <Recipe> recipes) {
            onRecipesFetched (recipes);
        }
    };


    public RecipesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_recipes, container, false);
        recipesFragmentViewModel = new ViewModelProvider(this).get (RecipesFragmentViewModel.class);

        mRecipesRv = v.findViewById (R.id.recipies_recycler_view);
        recipesAdapter = new RecipesAdapter (getContext (), mRecipesList, this);
        mRecipesRv.setAdapter (recipesAdapter);

        recipesFragmentViewModel.getRecipesAsync ();
        recipesFragmentViewModel.recipeLiveData.observe (getViewLifecycleOwner (), recipeObserver);
        return v;
    }

    void onRecipesFetched(List <Recipe> recipes) {
        mRecipesList.clear ();
        mRecipesList.addAll (recipes);
        recipesAdapter.notifyDataSetChanged ();
    }

    @Override
    public void onRecipeCardClicked(Recipe recipe) {
        RecipeDetailsFragment recipeDetailsFragment=new RecipeDetailsFragment ();
        Bundle bundle= new Bundle ();
        bundle.putParcelable ("RECIPE", Parcels.wrap (recipe));
        recipeDetailsFragment.setArguments (bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace (R.id.fragment_container,recipeDetailsFragment).commit ();
    }
}
