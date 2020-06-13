package com.geetha.bakingapp.ui.details;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Recipe;

import java.util.List;

public class RecipesAdapter extends RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder> {

    public interface RecipeClickCallback {
        void onRecipeCardClicked(Recipe recipe);
    }

    Context context;
    List <Recipe> mRecipeList;
    RecipeClickCallback callback;

    public RecipesAdapter(Context context, List <Recipe> mRecipeList, RecipeClickCallback callback) {
        this.mRecipeList = mRecipeList;
        this.context=context;
        this.callback = callback;
    }


    @NonNull
    @Override
    public RecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_recipie_card,parent,false);
        return new RecipesViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesViewHolder holder, final int position) {
        Glide.with (context).load (mRecipeList.get (position).getImage ()).into (holder.mRecipeImg);
        holder.mRecipeName.setText (mRecipeList.get (position).getName ());
        holder.mRecipeCard.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                callback.onRecipeCardClicked (mRecipeList.get (position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size ();
    }

    static class RecipesViewHolder extends RecyclerView.ViewHolder{

        CardView mRecipeCard;
        ImageView mRecipeImg;
        TextView mRecipeName;

        public RecipesViewHolder(@NonNull View itemView) {
            super (itemView);
            mRecipeCard=itemView.findViewById (R.id.recipe_card);
            mRecipeImg=itemView.findViewById (R.id.recipie_img_view);
            mRecipeName=itemView.findViewById (R.id.recipe_name_txt_view);
        }
    }
}
