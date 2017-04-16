package edu.pitt.cs.cs1635.cookingbuddy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class BuildActivity extends AppCompatActivity {

    static ArrayList steps = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.notrealltlistview);

        int stepNum = 1;
        if(((LinearLayout) linearLayout1).getChildCount() > 0){
            ((LinearLayout) linearLayout1).removeAllViews();
        }

        TextView et = new TextView(BuildActivity.this);
        et.setText("BUILD");
        et.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22);
        et.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout1.addView(et);

        for (Object obj: steps) {

            ImageView image = new ImageView(BuildActivity.this);
            image.setBackgroundResource(R.drawable.ic_launcher);
            et = new TextView(BuildActivity.this);
            et.setText("Step " + stepNum);
            et.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);
            Button btnt = new Button(BuildActivity.this);
            btnt.setText("Delete Step");
            btnt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!steps.isEmpty()){
                        steps.remove(steps.size() - 1);
                    }
                }
            });
            linearLayout1.addView(et);
            linearLayout1.addView(image);
            linearLayout1.addView(btnt);
            stepNum++;
        }

        final Button btn = (Button) findViewById(R.id.add_step_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                steps.add("");
                Intent myIntent = new Intent(BuildActivity.this, BuildStepActivity.class);
                BuildActivity.this.startActivity(myIntent);

            }
        });

        final Button done = (Button) findViewById(R.id.done_btn);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast toast = Toast.makeText(BuildActivity.this, "Recipe Saved!", Toast.LENGTH_LONG);
                toast.show();

            }
        });
    }
}