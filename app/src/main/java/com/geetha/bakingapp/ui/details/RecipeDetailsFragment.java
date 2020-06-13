package com.geetha.bakingapp.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.models.Step;
import com.geetha.bakingapp.ui.description.RecipeDescriptionFragment;

import org.parceler.Parcels;

import java.util.List;


public class RecipeDetailsFragment extends Fragment implements DescriptionAdapter.DescriptionClickCallback {

    Recipe mRecipe;
    RecyclerView mDescriptionRV;
    DescriptionAdapter descriptionAdapter;
    RecyclerView mIngredientsRV;
    IngredientsAdapter ingredientsAdapter;
    boolean mTwoPane;

    public RecipeDetailsFragment() { }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        mRecipe = Parcels.unwrap (getArguments ().getParcelable ("RECIPE"));
        mTwoPane = getArguments ().getBoolean ("TWO_PANE");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_recipe_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        mIngredientsRV = view.findViewById (R.id.ingredients_recycler_view);
        mDescriptionRV = view.findViewById (R.id.description_recycler_view);

        ingredientsAdapter = new IngredientsAdapter (mRecipe.getIngredients ());
        descriptionAdapter = new DescriptionAdapter (getContext (), mRecipe.getSteps (), this);
        mIngredientsRV.setAdapter (ingredientsAdapter);
        mDescriptionRV.setAdapter (descriptionAdapter);
    }

    @Override
    public void onDescriptionButtonClicked(List <Step> steps, int position) {
        RecipeDescriptionFragment recipeDescriptionFragment = new RecipeDescriptionFragment ();
        Bundle bundle = new Bundle ();
        bundle.putParcelable ("STEPS", Parcels.wrap (steps));
        bundle.putInt ("POSITION", position);
        recipeDescriptionFragment.setArguments (bundle);

        if (mTwoPane) {
            getActivity ().getSupportFragmentManager ()
                    .beginTransaction ()
                    .replace (R.id.recipe_detail_container, recipeDescriptionFragment)
                    .commit ();
        } else {
            getActivity ().getSupportFragmentManager ()
                    .beginTransaction ()
                    .replace (R.id.recipes_container, recipeDescriptionFragment)
                    .addToBackStack (null)
                    .commit ();
        }
    }

}
