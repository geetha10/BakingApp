package com.geetha.bakingapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipeDetailsViewModel extends ViewModel {

    private MutableLiveData <Recipe> _recipesLiveData = new MutableLiveData <> ();
    LiveData <Recipe> recipeLiveData = _recipesLiveData;


    void getRecipe(Recipe recipe) {
        _recipesLiveData.setValue (recipe);
    }
}
