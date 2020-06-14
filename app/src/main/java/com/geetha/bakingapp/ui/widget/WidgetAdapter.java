package com.geetha.bakingapp.ui.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Recipe;


import java.util.List;

public class WidgetAdapter extends RecyclerView.Adapter<WidgetAdapter.WidgetViewHolder> {

    public interface WidgetButtonClickCallback {
        void onButtonClicked(Recipe recipe);
    }

    List<Recipe> recipeList;
    WidgetButtonClickCallback callback;

    public WidgetAdapter(List <Recipe> recipeList, WidgetButtonClickCallback callback) {
        this.recipeList = recipeList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public WidgetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_widget_button,parent,false);
        return new WidgetViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull WidgetViewHolder holder, int position) {
        holder.mRecipeName.setText (recipeList.get (position).getName ());
        holder.mRecipeName.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                callback.onButtonClicked (recipeList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size ();
    }

    class WidgetViewHolder extends RecyclerView.ViewHolder{
        Button mRecipeName;
        public WidgetViewHolder(@NonNull View itemView) {
            super (itemView);
            mRecipeName=itemView.findViewById (R.id.widget_button);
        }

    }
}
