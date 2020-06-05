package com.geetha.bakingapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.models.Step;

import org.parceler.Parcels;



public class RecipeDetailsFragment extends Fragment implements DescriptionAdapter.DescriptionClickCallback {

    public  RecipeDetailsFragment(){}

    Recipe mRecipe;
    RecyclerView mDescriptionRV;
    DescriptionAdapter descriptionAdapter;
    RecyclerView mIngredientsRV;
    IngredientsAdapter ingredientsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate (R.layout.fragment_recipe_details,container,false);
        mRecipe= Parcels.unwrap (getArguments ().getParcelable ("RECIPE"));
        mIngredientsRV=view.findViewById (R.id.ingredients_recycler_view);
        ingredientsAdapter=new IngredientsAdapter (mRecipe.getIngredients ());
        mIngredientsRV.setAdapter (ingredientsAdapter);
        mDescriptionRV=view.findViewById (R.id.description_recycler_view);
        descriptionAdapter=new DescriptionAdapter (getContext (),mRecipe.getSteps (),this);
        mDescriptionRV.setAdapter (descriptionAdapter);
        return view;
    }


    @Override
    public void onDescriptionButtonClicked(Step step) {
        
        RecipeDescriptionFragment recipeDescriptionFragment=new RecipeDescriptionFragment ();
        Bundle bundle=new Bundle ();
        bundle.putParcelable ("STEP",Parcels.wrap (step));
        recipeDescriptionFragment.setArguments (bundle);
        getActivity ().getSupportFragmentManager ().beginTransaction ().
                replace (R.id.fragment_container,recipeDescriptionFragment).commit ();
    }

    /*RecipeDetailsFragment recipeDetailsFragment=new RecipeDetailsFragment ();
    Bundle bundle= new Bundle ();
        bundle.putParcelable ("RECIPE", Parcels.wrap (recipe));
        recipeDetailsFragment.setArguments (bundle);
    getActivity().getSupportFragmentManager().beginTransaction()
                .replace (R.id.fragment_container,recipeDetailsFragment).commit ();*/
}
