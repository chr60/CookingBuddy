package edu.pitt.cs.cs1635.cookingbuddy;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 15mik_000 on 3/27/2017.
 */

public class RecipeListAdapter extends BaseAdapter {

private Context mContext;
    private List<Recipe> mRecipeList;

    public RecipeListAdapter(Context context, List<Recipe> productList) {
        mContext = context;
        mRecipeList = productList;
    }

    @Override
    public int getCount() {
        return mRecipeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecipeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(mContext, R.layout.item_product_list,null);
        TextView tvName = (TextView)v.findViewById(R.id.recipe_name);
        //TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);

        tvName.setText(mRecipeList.get(position).getName());
       // tvPrice.setText(String.valueOf(mProductList.get(position).getPrice())+ " $");

        v.setTag(mRecipeList.get(position).getId());
        return v;
    }
}
