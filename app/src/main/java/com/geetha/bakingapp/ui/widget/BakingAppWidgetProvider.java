package com.geetha.bakingapp.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.widget.RemoteViews;

import com.geetha.bakingapp.Constants;
import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Recipe;
import com.google.gson.Gson;

/**
 * Implementation of App Widget functionality.
 */
public class BakingAppWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, String recipeString) {

        Intent intent = new Intent (context, ListViewWidgetService.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.putExtra (Constants.SHARED_PREF_SELECTED_RECIPE, recipeString);
        intent.setData(Uri.parse(intent.toUri(Intent.URI_INTENT_SCHEME)));

        Recipe r = new Gson ().fromJson (recipeString, Recipe.class);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews (context.getPackageName (), R.layout.widget_baking_app);
        views.setTextViewText (R.id.appwidget_text, r.getName ());
        views.setRemoteAdapter (R.id.widget_list, intent);
        views.setEmptyView (R.id.widget_list, R.id.widget_list_empty);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget (appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        SharedPreferences prefs = context.getSharedPreferences (Constants.SHARED_PREF_FILE_NAME, Context.MODE_PRIVATE);
        String selectedRecipe = prefs.getString (Constants.SHARED_PREF_SELECTED_RECIPE, "");
        if("".equals (selectedRecipe)){
            selectedRecipe = prefs.getString (Constants.SHARED_PREF_DEFAULT_RECIPE, "");
        }

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget (context, appWidgetManager, appWidgetId, selectedRecipe);
        }
    }
}

