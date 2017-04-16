package edu.pitt.cs.cs1635.cookingbuddy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;


/**
 * Created by Chevaughn.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static User user;
    public static final int TEXT_STEP_LENGTH = 17;
    public static final String PREFKEY = "HnBWVP7M9p7=Rr";
    private SharedPreferences sp;

    private final static HashMap<String, Object> navMap;
    static {
        navMap = new HashMap<String, Object>();
        navMap.put("build", BuildActivity.class);
        navMap.put("myrecipes", MyRecipesActivity.class);
        navMap.put("search", SearchActivity.class);
    }
    private static ArrayList<String> keys;
    static {
        keys = new ArrayList<>();
        keys.add("Beans");
        keys.add("Beans1");
        keys.add("Beans2");
        keys.add("Beans3");
    }

    private static String processStr(String s) {
        return s.replaceAll("[^a-zA-Z]", "").trim().toLowerCase();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //initPremadeRecipes();

        if (user == null) {
            runOnUiThread(new Runnable() {
                public void run() {
                    Intent intent = new Intent(MainActivity.this, mLoginActivity.class);
                    startActivity(intent);
                    user = new User("username");
                }
            });

        }
        else {

        }
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
        Log.d("pbt", pbt);
        Intent intent = new Intent(v.getContext(), (Class<?>) toActivity);
        startActivity(intent);
    }

    private void initPremadeRecipes() {

        HashMap<String, ArrayList<String>> map = new HashMap<>();

        final Random rand = new Random();
        int size = rand.nextInt() % 11;

        for (int i = 0; i < keys.size(); i++) {
            map.put(keys.get(i), generateSet(size));
        }

    }

    public ArrayList<String> generateSet(int num) {

        ArrayList arr = new ArrayList();
        final Random rand = new Random();
        for (int i = 0; i < num; i++) {
            int next = rand.nextInt() % 3;
            switch (next) {
                case 0:
                    arr.add(grt());
                    break;
                case 1:
                    ImageView img = new ImageView(this);
                    img.setImageResource(gfa());
                    arr.add(img);
                    break;
                case 2:
                    ImageView img2 = new ImageView(this);
                    img2.setImageResource(gri());
                    arr.add(img2);
            }
        }
        return arr;
    }

    public int gfa() {

        final Class drawableClass = R.drawable.class;
        final Field[] fields = drawableClass.getFields();
        Field mic = null;
        for (Field field : fields) {
            String str = field.getName();
            if (str.contains("audio")) {
                mic = field;
                break;
            }
        }
        int resID = 0;
        try {
            resID = mic.getInt(drawableClass);
            // img.setImageResource(resID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resID;

    }

    public TextView grt() {

        TextView tv = new TextView(this);
        tv.setLayoutParams(new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT));

        final String d_text = getString(R.string.recipe_text);
        String r_t = null;
        List<String> list = new ArrayList<>(Arrays.asList(d_text.split(" ")));
        Collections.shuffle(list);
        StringBuilder str = new StringBuilder();

        int i = 1;
        for (String line : list) {
            str.append(line);
            str.append(" ");
            if (i % TEXT_STEP_LENGTH == 0) {
                break;
            }
        }
        r_t = str.toString().replaceAll("[^\\sA-Za-z0-9]", "") + ".";
        tv.setText(r_t);

        return tv;
    }

    public int gri() {

        final Class drawableClass = R.drawable.class;
        final Field[] fields = drawableClass.getFields();

        ArrayList<Field> arr = new ArrayList<Field>();
        for (Field field : fields) {
            String str = field.getName();
            if (str.contains("image")) {
                arr.add(field);
            }
        }

        final Random rand = new Random();
        int rndInt = rand.nextInt(arr.size());
        int resID = 0;
        try {
            resID = arr.get(rndInt).getInt(drawableClass);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resID;
    }

    public void enableBtn(Button button) {
        if (!button.isEnabled()) {
            button.setClickable(true);
            button.setEnabled(true);
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
            Log.e("email_password", k.toString());
            return false;
        }
    }

}

class User {

    private static User singleton;
    private  ArrayList savedRecipies;
    private  ArrayList createdRecipies;

    private String username;

    public String getName() {
        return email;
    }

    private String email;
    private boolean isLoggedIn = false;

    public User(String uid){
        this.email = uid;
        singleton = this;
    }

    public static User getCurrentUser() {
        return singleton;
    }

    public boolean hasLoggedIn() {
        return isLoggedIn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

