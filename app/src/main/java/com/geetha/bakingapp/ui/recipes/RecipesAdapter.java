package com.geetha.bakingapp.ui.recipes;

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
        Recipe recipe = mRecipeList.get (position);
        if ("".equals (recipe.getImage ())) {
            Glide.with (context).load (getImageRes (recipe.getId ())).into (holder.mRecipeImg);
        } else {
            Glide.with (context).load (mRecipeList.get (position).getImage ()).into (holder.mRecipeImg);
        }

        holder.mRecipeName.setText (recipe.getName ());
        holder.mRecipeCard.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                callback.onRecipeCardClicked (recipe);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRecipeList.size ();
    }

    private int getImageRes(int recipeId){
        switch (recipeId){
            case 1:
                return R.drawable.nutella_pie;
            case 2:
                return R.drawable.brownies;
            case 3:
                return R.drawable.yellow_cake;
            case 4:
                return R.drawable.cheesecake;
            default:
                return R.drawable.gujiyaa;
        }
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
