package edu.pitt.cs.cs1635.cookingbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private final static HashMap<String, Object> navMap;

    static {
        navMap = new HashMap<String, Object>();
        navMap.put("build", BuildStepActivity.class);
        navMap.put("search", BuildStepActivity.class);
        navMap.put("myrecipes", MyRecipesActivity.class);
    }

    private static String processStr(String s) {
        return s.replaceAll("[^a-zA-Z]", "").trim().toLowerCase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        String pbt = processStr(((Button) v).getText().toString());
        Object toActivity = navMap.get(pbt);
        Log.e("pbt", pbt);
        Intent intent = new Intent(v.getContext(), (Class<?>) toActivity);
        startActivity(intent);
    }
}
