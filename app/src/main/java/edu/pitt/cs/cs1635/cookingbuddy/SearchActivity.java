package edu.pitt.cs.cs1635.cookingbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SearchActivity extends MainActivity {
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

    }

    public void newView(View view){
        EditText title_field = (EditText) findViewById(R.id.title_field);
        EditText ingredient_field = (EditText) findViewById(R.id.ingredient_field);
        EditText user_field = (EditText) findViewById(R.id.user_field);
        TextView error_message = (TextView) findViewById(R.id.error_message);

        if (title_field.getText().length() == 0 && ingredient_field.getText().length() == 0 && user_field.getText().length() == 0) {
            error_message.setText(R.string.search_error);
        } else {
            Intent intent = new Intent(this, Search_Results.class);
            startActivity(intent);
        }
    }

}
