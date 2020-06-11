package com.geetha.bakingapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geetha.bakingapp.models.Step;




public class RecipeDescriptionViewModel extends ViewModel {

    private MutableLiveData <Step> _recipeDesLiveData = new MutableLiveData <> ();
    LiveData <Step> recipeDesLiveData = _recipeDesLiveData;

    void getRecipe(Step step) {
        _recipeDesLiveData.setValue (step);
    }
}
