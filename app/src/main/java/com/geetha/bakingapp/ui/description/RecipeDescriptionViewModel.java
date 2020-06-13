package com.geetha.bakingapp.ui.description;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.geetha.bakingapp.models.Step;

import java.util.List;


public class RecipeDescriptionViewModel extends ViewModel {
    List <Step> steps;
    int position;

    private MutableLiveData <Step> _recipeDesLiveData = new MutableLiveData <> ();
    LiveData <Step> recipeDesLiveData = _recipeDesLiveData;

    private MutableLiveData<Boolean> _nextVisibleLiveData = new MutableLiveData <> ();
    LiveData<Boolean> nextVisibleLiveData = _nextVisibleLiveData;

    public RecipeDescriptionViewModel(List<Step> steps, int position) {
        this.position = position;
        this.steps = steps;

        _recipeDesLiveData.setValue (steps.get (position));
    }

    void onNextClicked(){
        if (position < steps.size () - 1) {
            position++;
            _recipeDesLiveData.setValue (steps.get (position));
        }

        if (position == steps.size () -1) {
            _nextVisibleLiveData.setValue (false);
        }
    }

    String getVideoUrl(Step step) {
        String videoUrl = "";
        if (step.getVideoURL () != null) {
            videoUrl = step.getVideoURL ();
        } else {
            videoUrl = step.getThumbnailURL ();
        }
        return videoUrl;
    }
}
