package com.geetha.bakingapp.ui.details;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.geetha.bakingapp.R;
import com.geetha.bakingapp.models.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {

    List <Ingredient> mIngredientList;

    public IngredientsAdapter(List <Ingredient> ingredientList) {
        this.mIngredientList = ingredientList;
    }


    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from (parent.getContext ()).inflate (R.layout.item_ingredients,parent,false);
        return new IngredientsViewHolder (view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        holder.mIngredientname.setText (mIngredientList.get (position).getIngredient ());
        holder.mIngredientQuantity.setText (mIngredientList.get (position).getQuantity () +
                mIngredientList.get (position).getMeasure ());
    }

    @Override
    public int getItemCount() {
        return mIngredientList.size ();
    }

    class IngredientsViewHolder extends RecyclerView.ViewHolder {
        CheckBox isChecked;
        TextView mIngredientname;
        TextView mIngredientQuantity;
        public IngredientsViewHolder(@NonNull View itemView) {
            super (itemView);
            isChecked=itemView.findViewById (R.id.ingredient_checkbox);
            mIngredientname=itemView.findViewById (R.id.ingredient_name_tv);
            mIngredientQuantity=itemView.findViewById (R.id.ingredient_quantity_tv);
        }
    }
}
