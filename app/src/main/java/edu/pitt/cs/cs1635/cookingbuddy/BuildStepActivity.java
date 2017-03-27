package edu.pitt.cs.cs1635.cookingbuddy;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

public class BuildStepActivity extends MainActivity implements View.OnClickListener {

    public static final int TEXT_STEP_LENGTH = 17;
    private static ArrayList<String> keys;

    static {
        keys = new ArrayList<>();
        keys.add("Beans");
        keys.add("Beans1");
        keys.add("Beans2");
        keys.add("Beans3");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_step);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        InitPremadeRecipes();

        final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                gri();
            }
        });

        // change for done button later
        final Button bt2 = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ;
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void InitPremadeRecipes() {

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
                    arr.add(gfa());
                    break;
                case 2:
                    arr.add(gri());
            }
        }
        return arr;
    }

    public ImageView gfa() {

        ImageView img = new ImageView(this);
        img = (ImageView) findViewById(R.id.imageView1);

        final Class drawableClass = R.drawable.class;
        final Field[] fields = drawableClass.getFields();
        Field mic = null;
        for (Field field : fields) {
            String str = field.getName();
            if (str.contains("mic")) {
                mic = field;
                break;
            }
        }
        try {
            img.setImageResource(mic.getInt(drawableClass));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return img;
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

    public ImageView gri() {

        ImageView img = (ImageView) findViewById(R.id.imageView1);

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
        try {
            int resID = arr.get(rndInt).getInt(drawableClass);
            img.setImageResource(resID);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return img;
    }

}
