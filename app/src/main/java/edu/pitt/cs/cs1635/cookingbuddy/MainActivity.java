package edu.pitt.cs.cs1635.cookingbuddy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String PREFKEY = "HnBWVP7M9p7=Rr";
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = this.getSharedPreferences(PREFKEY, Context.MODE_PRIVATE);

        if (true){
            Toast.makeText(this, "Starting...", Toast.LENGTH_LONG).show();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.getMessage();
            }
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {

            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }
    }

    SharedPreferences getSp(){
        return sp;
    }

    void s(String k, String v){

        SharedPreferences.Editor editor = sp.edit();
        editor.putString(k, v);
        editor.commit();

    }

    void r(String k){

        SharedPreferences.Editor editor = sp.edit();
        editor.remove(k);
        editor.commit();

    }

    public boolean isValidPair(String e, String p){
        try{
            return sp.getAll().get(e).equals(p);
        }
        catch (Exception k){
            Log.e("email_password", k.getMessage());
            return false;
        }
    }

}

class User {
    private int uid;
    private boolean isLoggedIn = false;

    public User(int uid){
        this.uid = uid;
    }

    public boolean hasLoggedIn() {
        return isLoggedIn;
    }
}