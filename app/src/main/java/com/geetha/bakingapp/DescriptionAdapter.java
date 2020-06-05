package com.geetha.bakingapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.geetha.bakingapp.models.Step;

import java.util.List;


public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.DescriptionViewHolder> {

    private static final String TAG = "Description Adapter";

    public DescriptionAdapter(List <Step> mStepsList) {
        this.mStepsList = mStepsList;
    }

    List <Step> mStepsList;

    @NonNull
    @Override
    public DescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_step,parent,false);
        return new DescriptionViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescriptionViewHolder holder, int position) {
        holder.mDescriptionBtn.setText ("Step "+ String.valueOf (position+1));
    }

    @Override
    public int getItemCount() {
        return mStepsList.size ();
    }

    class DescriptionViewHolder extends RecyclerView.ViewHolder{

        Button mDescriptionBtn;

        public DescriptionViewHolder(@NonNull View itemView) {
            super (itemView);
            mDescriptionBtn =itemView.findViewById (R.id.description_btn);
        }
    }
}
