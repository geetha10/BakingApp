package com.geetha.bakingapp.ui.widget;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.geetha.bakingapp.Constants;
import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Recipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.geetha.bakingapp.ui.widget.BakingAppWidgetProvider.updateAppWidget;

public class WidgetConfigureActivity extends AppCompatActivity implements WidgetAdapter.WidgetButtonClickCallback {

    RecyclerView widgetButtonRv;
    List <Recipe> recipeList = new ArrayList <> ();
    private int appWidgetId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_widget_configure);

        Intent configIntent = getIntent ();
        Bundle extras = configIntent.getExtras ();
        if (extras != null) {
            appWidgetId = extras.getInt (AppWidgetManager.EXTRA_APPWIDGET_ID,
                    AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        Intent resultValue = new Intent ();
        resultValue.putExtra (AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult (RESULT_CANCELED, resultValue);
        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish ();
        }

        getRecipes ();

        widgetButtonRv = findViewById (R.id.widget_rv);
        WidgetAdapter widgetAdapter = new WidgetAdapter (recipeList, this);
        widgetButtonRv.setAdapter (widgetAdapter);
    }

    private void getRecipes() {
        SharedPreferences preferences = getSharedPreferences (Constants.SHARED_PREF_FILE_NAME,
                Context.MODE_PRIVATE);
        String recipeString = preferences.getString (Constants.SHARED_PREF_RECIPES_KEY, "");
        if(!TextUtils.isEmpty (recipeString)){
            Type listType = new TypeToken <ArrayList <Recipe>> () {}.getType ();
            List <Recipe> recipes = new Gson ().fromJson (recipeString, listType);
            if (recipes != null) {
                recipeList.addAll (recipes);

                Recipe r = recipes.get (0);
                String defRecipe = new Gson ().toJson (r, Recipe.class);
                preferences.edit ()
                        .putString (Constants.SHARED_PREF_DEFAULT_RECIPE, defRecipe)
                        .apply ();
            }
        }
    }

    @Override
    public void onButtonClicked(Recipe recipe) {
        String selectedRecipe = new Gson ().toJson (recipe, Recipe.class);

        getSharedPreferences (Constants.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE)
                .edit ()
                .putString (Constants.SHARED_PREF_SELECTED_RECIPE, selectedRecipe)
                .apply ();

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
        updateAppWidget(this, appWidgetManager, appWidgetId, selectedRecipe);

        Intent resultValue = new Intent();
        resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        setResult(RESULT_OK, resultValue);
        finish();
    }
}