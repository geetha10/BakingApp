package com.geetha.bakingapp;

import android.content.Intent;

import androidx.test.espresso.intent.rule.IntentsTestRule;


import com.geetha.bakingapp.models.Ingredient;
import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.ui.activities.RecipeActivity;
import com.google.gson.Gson;

import org.junit.Rule;
import org.junit.Test;
import org.parceler.Parcels;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RecipeActivityEspressoTest {

    @Rule
    public IntentsTestRule<RecipeActivity> intentRule=
            new IntentsTestRule <RecipeActivity> (RecipeActivity.class){
                @Override
                protected Intent getActivityIntent(){
                    Intent intent=new Intent ();
                    intent.putExtra ("RECIPE", Parcels.wrap (getRecipe ()));
                    return intent;
                }
            };

    @Test
    public void ensureRecyclerViewIsPresent() {
        onView (withId (R.id.ingredients_recycler_view))
                .check (matches ((isDisplayed ())));
        onView (withId (R.id.description_recycler_view))
                .check (matches ((isDisplayed ())));
    }

    private Recipe getRecipe(){
        String recipeString = "{\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Nutella Pie\",\n" +
                "    \"ingredients\": [\n" +
                "      {\n" +
                "        \"quantity\": 2,\n" +
                "        \"measure\": \"CUP\",\n" +
                "        \"ingredient\": \"Graham Cracker crumbs\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"quantity\": 6,\n" +
                "        \"measure\": \"TBLSP\",\n" +
                "        \"ingredient\": \"unsalted butter, melted\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"steps\": [\n" +
                "      {\n" +
                "        \"id\": 0,\n" +
                "        \"shortDescription\": \"Recipe Introduction\",\n" +
                "        \"description\": \"Recipe Introduction\",\n" +
                "        \"videoURL\": \"https://d17h27t6h515a5.cloudfront.net/topher/2017/April/58ffd974_-intro-creampie/-intro-creampie.mp4\",\n" +
                "        \"thumbnailURL\": \"\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"shortDescription\": \"Starting prep\",\n" +
                "        \"description\": \"1. Preheat the oven to 350\\u00b0F. Butter a 9\\\" deep dish pie pan.\",\n" +
                "        \"videoURL\": \"\",\n" +
                "        \"thumbnailURL\": \"\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"servings\": 8,\n" +
                "    \"image\": \"\"\n" +
                "  }";
        return new Gson ().fromJson (recipeString, Recipe.class);
    }
}
