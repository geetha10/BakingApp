package com.geetha.bakingapp;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.geetha.bakingapp.models.Step;

import org.parceler.Parcels;

public class RecipeDescriptionFragment extends Fragment {

    public RecipeDescriptionFragment() {
    }

    Step step;
    VideoView mDescriptionVideoView;
    TextView mDescriptionTextView;
    TextView mDescriptionHeader;
    Button mNextStepButton;
    Uri videoUri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate (R.layout.description,container,false);
        step= Parcels.unwrap (getArguments ().getParcelable ("STEP"));
        videoUri=Uri.parse (step.getVideoURL ());
        mDescriptionTextView=view.findViewById (R.id.step_description_textview);
        mDescriptionVideoView=view.findViewById (R.id.step_description_videoView);
        mDescriptionVideoView.setVideoURI (videoUri);
        mDescriptionHeader=view.findViewById (R.id.step_description_header);
        mNextStepButton=view.findViewById (R.id.next_step_btn);
        mDescriptionHeader.setText (step.getShortDescription ());
        mDescriptionTextView.setText (step.getDescription ());
        return view;
    }
}
