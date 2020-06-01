package com.geetha.bakingapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipiesFragment extends Fragment {
    RecyclerView mRecipiesRv;
    RecipesAdapter recipesAdapter;
    List<Recipe> mRecipesList= new ArrayList <> ();
    public RecipiesFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate (R.layout.fragment_recipes,container,false);
        mRecipiesRv=v.findViewById (R.id.recipies_recycler_view);
        recipesAdapter= new RecipesAdapter (getContext (),mRecipesList);
        mRecipiesRv.setAdapter (recipesAdapter);
        RetrofitInstance.get ().getAllRecipes ().enqueue (new Callback <List <Recipe>> () {
            @Override
            public void onResponse(Call <List <Recipe>> call, Response <List <Recipe>> response) {
                mRecipesList.addAll (response.body ());
                Toast.makeText (getContext (),"Success",Toast.LENGTH_SHORT).show ();
                recipesAdapter.notifyDataSetChanged ();
            }

            @Override
            public void onFailure(Call <List <Recipe>> call, Throwable t) {
                Toast.makeText (getContext (),"Failure",Toast.LENGTH_SHORT).show ();
            }
        });
        return v;
    }
}
