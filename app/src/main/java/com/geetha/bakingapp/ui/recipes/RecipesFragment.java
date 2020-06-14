package com.geetha.bakingapp.ui.recipes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import com.geetha.bakingapp.Constants;
import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.ui.activities.RecipeActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.parceler.Parcels;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment implements RecipesAdapter.RecipeClickCallback {
    RecyclerView mRecipesRv;
    RecipesAdapter recipesAdapter;
    List <Recipe> mRecipesList = new ArrayList <> ();
    Observer <List <Recipe>> recipeObserver = new Observer <List <Recipe>> () {
        @Override
        public void onChanged(List <Recipe> recipes) {
            Type listType = new TypeToken <ArrayList <Recipe>> () {}.getType ();
            String recipeString = new Gson ().toJson (recipes, listType);
            saveRecipes (recipeString);

            onRecipesFetched (recipes);
        }
    };

    private RecipesFragmentViewModel recipesFragmentViewModel;

    public RecipesFragment() {

    }

    private void saveRecipes(String recipesString) {
        SharedPreferences saveRecipesString = getContext ()
                .getSharedPreferences (Constants.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = saveRecipesString.edit ();
        editor.putString (Constants.SHARED_PREF_RECIPES_KEY, recipesString);
        editor.apply ();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate (R.layout.fragment_recipes, container, false);
        recipesFragmentViewModel = new ViewModelProvider (this)
                .get (RecipesFragmentViewModel.class);

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

        Recipe r = mRecipesList.get (0);
        String defaultRecipeString = new Gson ().toJson (r, Recipe.class);
        getActivity ()
                .getSharedPreferences (Constants.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
                .edit ().putString (Constants.SHARED_PREF_DEFAULT_RECIPE, defaultRecipeString)
                .apply ();
    }

    @Override
    public void onRecipeCardClicked(Recipe recipe) {
        Intent recipeIntent = new Intent (getContext (), RecipeActivity.class);
        recipeIntent.putExtra ("RECIPE", Parcels.wrap (recipe));
        startActivity (recipeIntent);
    }
}
