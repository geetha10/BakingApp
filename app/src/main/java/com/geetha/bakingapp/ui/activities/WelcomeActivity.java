package com.geetha.bakingapp.ui.activities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.geetha.bakingapp.R;
import com.geetha.bakingapp.ui.recipes.RecipesFragment;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.welcome_activity);

        if (savedInstanceState != null) {
                return;
            }
            RecipesFragment recipeFragment = new RecipesFragment ();
            getSupportFragmentManager ().beginTransaction ().add (R.id.fragment_container, recipeFragment).commit ();
        }

}
