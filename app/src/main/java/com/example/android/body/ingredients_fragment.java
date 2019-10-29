package com.example.android.body;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakingapp.R;
import com.example.android.body.Adapter.IngredientsAdapter;
import com.example.android.body.Model.ingredientsModel;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class ingredients_fragment extends Fragment {
    public ingredients_fragment() {

    }
    RecyclerView ingredientsView;
   private IngredientsAdapter ingredientsAdapter;
  private   ArrayList<ingredientsModel> ingredients=new ArrayList<>();



    public static ingredients_fragment newObject(ArrayList<ingredientsModel> ingredients) {
        ingredients_fragment ingredientFragment = new ingredients_fragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ingredients", ingredients);
        ingredientFragment.setArguments(bundle);
        return ingredientFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.ingredient_fragment, container, false);
        ButterKnife.bind(this, rootView);


        if (getArguments() != null) {
            ingredients = getArguments().getParcelableArrayList("ingredients");
        }
        ingredientsView = (RecyclerView) rootView.findViewById(R.id.rv_ingredient);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(),1);
        ingredientsView.setLayoutManager(layoutManager);

            ingredientsAdapter = new IngredientsAdapter(ingredients, getContext());
            ingredientsView.setAdapter(ingredientsAdapter);

        return rootView;

    }


}
