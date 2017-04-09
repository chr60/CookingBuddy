package edu.pitt.cs.cs1635.cookingbuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyRecipesActivity extends MainActivity {

    private ListView lvProduct;
    private RecipeListAdapter adapter;
    private List<Recipe> mRecipeList;

    private ListView lvProduct2;
    private RecipeListAdapter adapter2;
    private List<Recipe> mRecipeList2;


    private ListView lvProduct3;
    private RecipeListAdapter adapter3;
    private List<Recipe> mRecipeList3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        lvProduct = (ListView)findViewById(R.id.listview_product);
        mRecipeList = new ArrayList<>();

        mRecipeList.add(new Recipe(1,"Chicken Stew"));
        mRecipeList.add(new Recipe(2,"Beef and soup"));
        mRecipeList.add(new Recipe(3,"Peas and Rice"));
        mRecipeList.add(new Recipe(4,"Tomatoes and Lettuce"));
        mRecipeList.add(new Recipe(5,"Hot dog"));
        mRecipeList.add(new Recipe(6,"Cheeseburger"));
        mRecipeList.add(new Recipe(7,"Pad Thai"));
        mRecipeList.add(new Recipe(8,"Turkey Sandwhich"));
        mRecipeList.add(new Recipe(9,"Sushi and rice"));


        adapter = new RecipeListAdapter(getApplicationContext(),mRecipeList);
        lvProduct.setAdapter(adapter);

        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Clicked product id=" + view.getTag(),Toast.LENGTH_SHORT).show();
            }
        });





        lvProduct2 = (ListView)findViewById(R.id.listview_product2);
        mRecipeList2 = new ArrayList<>();

        mRecipeList2.add(new Recipe(1,"Chicken Stew"));
        mRecipeList2.add(new Recipe(2,"Beef and soup"));
        mRecipeList2.add(new Recipe(3,"Peas and Rice"));
        mRecipeList2.add(new Recipe(4,"Tomatoes and Lettuce"));
        mRecipeList2.add(new Recipe(5,"Hot dog"));
        mRecipeList2.add(new Recipe(6,"Cheeseburger"));
        mRecipeList2.add(new Recipe(7,"Pad Thai"));
        mRecipeList2.add(new Recipe(8,"Turkey Sandwhich"));
        mRecipeList2.add(new Recipe(9,"Sushi and rice"));


        adapter2 = new RecipeListAdapter(getApplicationContext(),mRecipeList2);
        lvProduct2.setAdapter(adapter2);

        lvProduct2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Clicked product id=" + view.getTag(),Toast.LENGTH_SHORT).show();
            }
        });




        lvProduct3 = (ListView)findViewById(R.id.listview_product3);
        mRecipeList3 = new ArrayList<>();

        mRecipeList3.add(new Recipe(1,"Chicken Stew"));
        mRecipeList3.add(new Recipe(2,"Beef and soup"));
        mRecipeList3.add(new Recipe(3,"Peas and Rice"));
        mRecipeList3.add(new Recipe(4,"Tomatoes and Lettuce"));
        mRecipeList3.add(new Recipe(5,"Hot dog"));
        mRecipeList3.add(new Recipe(6,"Cheeseburger"));
        mRecipeList3.add(new Recipe(7,"Pad Thai"));
        mRecipeList3.add(new Recipe(8,"Turkey Sandwhich"));
        mRecipeList3.add(new Recipe(9,"Sushi and rice"));


        adapter3 = new RecipeListAdapter(getApplicationContext(),mRecipeList3);
        lvProduct3.setAdapter(adapter3);

        lvProduct3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),"Clicked product id=" + view.getTag(),Toast.LENGTH_SHORT).show();
            }
        });




    }


}
