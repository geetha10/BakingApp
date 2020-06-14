package com.geetha.bakingapp.ui.widget;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.geetha.bakingapp.Constants;
import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Ingredient;
import com.geetha.bakingapp.models.Recipe;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class ListViewWidgetService extends RemoteViewsService {
    private static final String TAG = "ListViewWidgetService";
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        Log.d (TAG, "onGetViewFactory: ");
        return new ListViewRemoteViewsFactory (this.getApplicationContext (), intent);
    }

    static class ListViewRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
        private Context mContext;
        private List <Ingredient> ingredients = new ArrayList <> ();
        String recipeString;
        Recipe recipe;

        private static final String TAG = "ListViewRemoteViewsFact";

        public ListViewRemoteViewsFactory(Context context, Intent intent) {
            this.mContext = context;
            recipeString = intent.getExtras ().getString (Constants.SHARED_PREF_SELECTED_RECIPE, "");
            recipe = new Gson ().fromJson (recipeString, Recipe.class);
        }

        @Override
        public void onCreate() {

        }

        @Override
        public void onDataSetChanged() {
            ingredients.clear ();
            ingredients.addAll (recipe.getIngredients ());
        }

        @Override
        public void onDestroy() {

        }

        @Override
        public int getCount() {
            return ingredients.size ();
        }

        @Override
        public RemoteViews getViewAt(int position) {
            if (position == AdapterView.INVALID_POSITION) {
                return null;
            }

            RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.item_ingredient_widget);
            rv.setTextViewText (R.id.widget_list_item_tv, getIngredientString(ingredients.get (position)));
            return rv;
        }

        String getIngredientString(Ingredient ingredient){
            return String.format("%s %s of %s",
                    ingredient.getQuantity (),
                    ingredient.getMeasure (),
                    ingredient.getIngredient ()
            );
        }

        @Override
        public RemoteViews getLoadingView() {
            return null;
        }

        @Override
        public int getViewTypeCount() {
            return 1;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }
    }
}
