package com.geetha.bakingapp;

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

import com.geetha.bakingapp.models.Step;
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

public class RecipeDescriptionFragment extends Fragment {

    List <Step> steps;
    Step step;
    int position;
    TextView mDescriptionTextView;
    TextView mDescriptionHeader;
    Button mNextStepButton;
    Uri videoUri;
    int appNameStringRes;
    SimpleExoPlayer absPlayerInternal;
    PlayerView mDescriptionVideoView;
    String url;

    public RecipeDescriptionFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        steps = Parcels.unwrap (getArguments ().getParcelable ("STEP"));
        position=getArguments ().getInt ("POSITION");
        step=steps.get (position);
        setMediaPlayer ();
        appNameStringRes = R.string.app_name;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate (R.layout.fragment_description, container, false);
        mDescriptionTextView = view.findViewById (R.id.step_description_textview);
        mDescriptionVideoView = view.findViewById (R.id.step_description_videoView);
        mDescriptionHeader = view.findViewById (R.id.step_description_header);
        mNextStepButton = view.findViewById (R.id.next_step_btn);
        if("".equals (url)){
            mDescriptionVideoView.setVisibility (View.GONE);
        }
        else {
            TrackSelector trackSelectorDef = new DefaultTrackSelector ();
            absPlayerInternal = ExoPlayerFactory.newSimpleInstance(getContext (), trackSelectorDef);
            String userAgent = Util.getUserAgent(getContext (), this.getString(appNameStringRes));
            DefaultDataSourceFactory defaultDataSourceFactory=new DefaultDataSourceFactory (getContext (),userAgent);
            MediaSource mediaSource = new ProgressiveMediaSource.Factory(defaultDataSourceFactory).createMediaSource(videoUri);  // creating a media source

            absPlayerInternal.prepare(mediaSource);
            absPlayerInternal.setPlayWhenReady(false);
            mDescriptionVideoView.setPlayer (absPlayerInternal);
        }
        mDescriptionHeader.setText (step.getShortDescription ());
        mDescriptionTextView.setText (step.getDescription ());
        return view;
    }

    void setMediaPlayer(){

        if(step.getVideoURL () != null){
            url=step.getVideoURL ();
        }
        else
           url= step.getThumbnailURL ();
        videoUri = Uri.parse (step.getVideoURL ());
    }
}
