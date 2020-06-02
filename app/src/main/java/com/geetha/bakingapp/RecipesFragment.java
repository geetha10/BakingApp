package com.geetha.bakingapp;

import android.os.Bundle;
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

import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment {
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
        recipesAdapter = new RecipesAdapter (getContext (), mRecipesList);
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
}
