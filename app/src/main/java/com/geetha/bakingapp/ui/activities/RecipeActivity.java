package com.geetha.bakingapp.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.models.Step;
import com.geetha.bakingapp.ui.description.RecipeDescriptionFragment;
import com.geetha.bakingapp.ui.details.RecipeDetailsFragment;

import org.parceler.Parcels;

import java.util.List;

public class RecipeActivity extends AppCompatActivity {

    boolean mTwoPane;
    Recipe recipe;
    List <Step> steps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);

        setContentView (R.layout.activity_recipe);
        if (savedInstanceState != null) {
            return;
        }
        recipe = Parcels.unwrap (getIntent ().getExtras ().getParcelable ("RECIPE"));
        if (findViewById (R.id.recipe_detail_container) != null) {
            mTwoPane = true;
        }

        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment ();
        Bundle bundle = new Bundle ();
        bundle.putParcelable ("RECIPE", Parcels.wrap (recipe));
        bundle.putBoolean ("TWO_PANE", mTwoPane);
        recipeDetailsFragment.setArguments (bundle);
        getSupportFragmentManager ()
                .beginTransaction ()
                .add (R.id.recipes_container, recipeDetailsFragment)
                .addToBackStack (null)
                .commit ();

        if (mTwoPane) {
            RecipeDescriptionFragment recipeDescriptionFragment = new RecipeDescriptionFragment ();
            steps = recipe.getSteps ();
            Bundle descriptionBundle = new Bundle ();
            descriptionBundle.putParcelable ("STEPS", Parcels.wrap (steps));
            descriptionBundle.putInt ("POSITION", 0);
            recipeDescriptionFragment.setArguments (descriptionBundle);
            getSupportFragmentManager ()
                    .beginTransaction ()
                    .add (R.id.recipe_detail_container, recipeDescriptionFragment)
                    .commit ();
        }
    }
}