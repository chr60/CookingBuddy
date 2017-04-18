package edu.pitt.cs.cs1635.cookingbuddy;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.HashSet;

/**
 * Created by Chevaughn.
 */

public class SearchActivityHandler extends SearchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final EditText title = (EditText) findViewById(R.id.title_field);

        title.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                validateSearchQuery();
            }
        });

        final EditText ingredient = (EditText) findViewById(R.id.ingredient_field);

        ingredient.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                validateSearchQuery();
            }
        });

        final EditText username = (EditText) findViewById(R.id.user_field);

        username.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                validateSearchQuery();
            }
        });

    }

    private void search() {
        EditText title = (EditText) findViewById(R.id.title_field);
        EditText ingredients = (EditText) findViewById(R.id.ingredient_field);
        EditText userName = (EditText) findViewById(R.id.user_field);

        HashSet terms = new HashSet();

        for (String str : title.getText().toString().replace(',', ' ').split(" ")){
            terms.add(processStr(str));
        }

        for (String str : ingredients.getText().toString().replace(',', ' ').split(" ")){
            terms.add(processStr(str));
        }

        for (String str : userName.getText().toString().replace(',', ' ').split(" ")){
            terms.add(processStr(str));
        }

        for (Object str : terms){
            Log.d("terms", str.toString());
        }

       // fetchRecipesByTerms()
    }


    @Override
    public void newView(View view){
        // wrapper for michael's legacy code
        mOnClickHandler(view);
    }

    private void mOnClickHandler(View view){

        search();

    }

    private void validateSearchQuery() {

        EditText title = (EditText) findViewById(R.id.title_field);
        EditText ingredients = (EditText) findViewById(R.id.ingredient_field);
        EditText userName = (EditText) findViewById(R.id.user_field);

        Button button = (Button) findViewById(R.id.search_button);

        if (processStr(title.getText().toString()).isEmpty()
                && processStr(ingredients.getText().toString()).isEmpty()
                && processStr(userName.getText().toString()).isEmpty()){
            if (button.isEnabled()) {
                button.setClickable(false);
                button.setEnabled(false);
            }
        }
        else {
                if (!button.isEnabled()) {
                    button.setClickable(true);
                    button.setEnabled(true);
                }
            }
    }

}
