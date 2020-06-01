package com.geetha.bakingapp.network;

import com.geetha.bakingapp.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeService {

    @GET("baking.json")
    Call <List <Recipe>> getAllRecipes();
}
