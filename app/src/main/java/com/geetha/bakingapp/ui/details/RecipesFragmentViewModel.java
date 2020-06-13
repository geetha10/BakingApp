package com.geetha.bakingapp.ui.details;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesFragmentViewModel extends ViewModel {

    private MutableLiveData <List <Recipe>> _recipesLiveData = new MutableLiveData <> ();
    LiveData <List <Recipe>> recipeLiveData = _recipesLiveData;

    void getRecipesAsync() {
        RetrofitInstance.get ().getAllRecipes ().enqueue (new Callback <List <Recipe>> () {
            @Override
            public void onResponse(Call <List <Recipe>> call, Response <List <Recipe>> response) {
                _recipesLiveData.setValue (response.body ());
            }

            @Override
            public void onFailure(Call <List <Recipe>> call, Throwable t) {

            }
        });
    }
}
