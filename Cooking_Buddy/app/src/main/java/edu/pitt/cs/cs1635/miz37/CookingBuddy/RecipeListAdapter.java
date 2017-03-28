package edu.pitt.cs.cs1635.miz37.CookingBuddy;

/**
 * Created by Michael on 3/28/2017.
 */

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class RecipeListAdapter extends BaseAdapter{

    private Context mContext;
    private List<Recipe> mRecipeList;
    private List<Recipe> mUserList;

    public RecipeListAdapter(Context context, List<Recipe> productList, List<Recipe> userList) {
        mContext = context;
        mRecipeList = productList;
        mUserList = userList;
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
        View v = View.inflate(mContext,R.layout.item_product_list,null);
        TextView tvName = (TextView)v.findViewById(R.id.recipe_name);
        TextView userName = (TextView)v.findViewById(R.id.user_name);
        //TextView tvPrice = (TextView)v.findViewById(R.id.tv_price);

        tvName.setText(mRecipeList.get(position).getName());
        userName.setText(mUserList.get(position).getName());
        // tvPrice.setText(String.valueOf(mProductList.get(position).getPrice())+ " $");

        v.setTag(mRecipeList.get(position).getId());
        return v;
    }
}
