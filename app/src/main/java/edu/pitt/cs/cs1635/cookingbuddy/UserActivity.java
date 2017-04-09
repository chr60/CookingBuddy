package edu.pitt.cs.cs1635.cookingbuddy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    private ListView lvProduct;
    private RecipeListAdapter adapter;
    private List<Recipe> mRecipeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

    }


}
