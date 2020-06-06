package com.geetha.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geetha.bakingapp.models.Recipe;
import com.geetha.bakingapp.models.Step;

import java.util.List;


public class DescriptionAdapter extends RecyclerView.Adapter<DescriptionAdapter.DescriptionViewHolder> {

    public interface DescriptionClickCallback {
        void onDescriptionButtonClicked(List<Step> steps,int position);
    }

    Context context;
    List <Step> mStepsList;

    public DescriptionAdapter(Context context, List <Step> mStepsList, DescriptionClickCallback callback) {
        this.context = context;
        this.mStepsList = mStepsList;
        this.callback = callback;
    }

    DescriptionClickCallback callback;

    @NonNull
    @Override
    public DescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_step,parent,false);
        return new DescriptionViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull DescriptionViewHolder holder, final int position) {
        holder.mDescriptionBtn.setText ("Step "+ String.valueOf (position+1));
        holder.mDescriptionBtn.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                callback.onDescriptionButtonClicked (mStepsList,position);
            }
        });
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
