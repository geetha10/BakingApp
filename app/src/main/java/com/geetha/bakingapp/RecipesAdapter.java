package com.geetha.bakingapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geetha.bakingapp.models.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    Context context;
    List <Recipe> mRecipeList;

    public RecipesAdapter(Context context, List <Recipe> mRecipeList) {
        this.mRecipeList = mRecipeList;
        this.context=context;
    }


    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.recipie_card,parent,false);
        return new RecipesViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, int position) {
        Glide.with (context).load (mRecipeList.get (position).getImage ()).into (holder.mRecipeImg);
        holder.mRecipeName.setText (mRecipeList.get (position).getName ());
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size ();
    }

    static class RecipesViewHolder extends RecyclerView.ViewHolder{

        ImageView mRecipeImg;
        TextView mRecipeName;

        public RecipesViewHolder(@NonNull View itemView) {
            super (itemView);
            mRecipeImg=itemView.findViewById (R.id.recipie_img_view);
            mRecipeName=itemView.findViewById (R.id.recipe_name_txt_view);
        }
    }
}
