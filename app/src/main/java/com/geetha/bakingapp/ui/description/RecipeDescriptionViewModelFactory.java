package com.geetha.bakingapp.ui.description;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.geetha.bakingapp.models.Step;

import java.util.List;

public class RecipeDescriptionViewModelFactory implements ViewModelProvider.Factory {

    List <Step> steps;
    int position;

    public RecipeDescriptionViewModelFactory(List <Step> steps, int position) {
        this.steps = steps;
        this.position = position;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class <T> modelClass) {
        return (T) new RecipeDescriptionViewModel (steps, position);
    }
}
