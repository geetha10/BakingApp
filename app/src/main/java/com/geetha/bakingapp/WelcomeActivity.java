package com.geetha.bakingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WelcomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.welcome_activity);

        RecipesFragment recipeFragment=new RecipesFragment ();
        getSupportFragmentManager ().beginTransaction ().add (R.id.fragment_container,recipeFragment).commit ();
    }
}
