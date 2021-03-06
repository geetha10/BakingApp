package com.geetha.bakingapp.ui.description;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Step;
import com.geetha.bakingapp.ui.description.RecipeDescriptionViewModel;
import com.geetha.bakingapp.ui.description.RecipeDescriptionViewModelFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.parceler.Parcels;

import java.util.List;

public class RecipeDescriptionFragment extends Fragment implements View.OnClickListener {

    List <Step> steps;
    int position;

    TextView mDescriptionTextView;
    TextView mDescriptionHeader;
    Button mNextStepButton;

    int appNameStringRes;
    SimpleExoPlayer absPlayerInternal;
    PlayerView mDescriptionVideoView;

    Observer <Step> recipeStepObserver = new Observer <Step> () {
        @Override
        public void onChanged(Step step) {
            populateUI (step);
        }
    };

    private RecipeDescriptionViewModel recipeDescriptionViewModel;

    public RecipeDescriptionFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        position = getArguments ().getInt ("POSITION");
        steps = Parcels.unwrap (getArguments ().getParcelable ("STEPS"));
        appNameStringRes = R.string.app_name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.fragment_description, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        mDescriptionTextView = view.findViewById (R.id.step_description_textview);
        mDescriptionVideoView = view.findViewById (R.id.step_description_videoView);
        mDescriptionHeader = view.findViewById (R.id.step_description_header);
        mNextStepButton = view.findViewById (R.id.next_step_btn);
        mNextStepButton.setOnClickListener (this);

        ViewModelProvider.Factory factory = new RecipeDescriptionViewModelFactory (steps, position);
        recipeDescriptionViewModel = new ViewModelProvider (this, factory)
                .get (RecipeDescriptionViewModel.class);
        recipeDescriptionViewModel.recipeDesLiveData.observe (getViewLifecycleOwner (), recipeStepObserver);

        recipeDescriptionViewModel.nextVisibleLiveData.observe (getViewLifecycleOwner (), new Observer <Boolean> () {
            @Override
            public void onChanged(Boolean visible) {
                mNextStepButton.setVisibility (!visible ? View.GONE : View.VISIBLE);
            }
        });
    }

    private void populateUI(Step step) {
        String videoUrl = recipeDescriptionViewModel.getVideoUrl (step);
        if ("".equals (videoUrl)) {
            mDescriptionVideoView.setVisibility (View.GONE);
        } else {
            mDescriptionVideoView.setVisibility (View.VISIBLE);
            setMediaPlayer (videoUrl);
        }
        mDescriptionHeader.setText (step.getShortDescription ());
        mDescriptionTextView.setText (step.getDescription ());
    }

    private void setMediaPlayer(String videoUrl) {
        TrackSelector trackSelectorDef = new DefaultTrackSelector ();
        absPlayerInternal = ExoPlayerFactory.newSimpleInstance (getContext (), trackSelectorDef);
        String userAgent = Util.getUserAgent (getContext (), this.getString (appNameStringRes));
        DefaultDataSourceFactory defaultDataSourceFactory = new DefaultDataSourceFactory (getContext (), userAgent);
        MediaSource mediaSource = new ProgressiveMediaSource.Factory (defaultDataSourceFactory)
                .createMediaSource (Uri.parse (videoUrl));  // creating a media source

        absPlayerInternal.prepare (mediaSource);
        absPlayerInternal.setPlayWhenReady (false);
        mDescriptionVideoView.setPlayer (absPlayerInternal);
    }

    @Override
    public void onClick(View v) {
        if (v.getId () == R.id.next_step_btn) {
            recipeDescriptionViewModel.onNextClicked ();
        }
    }
}
