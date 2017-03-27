package edu.pitt.cs.cs1635.cookingbuddy;

import android.content.DialogInterface;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TabHost;

/**
 * Created by Chevaughn.
 */
public class BuildStepActivity extends MainActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_tabs);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initTabs();

        final ImageView mImageView = (ImageView) findViewById(R.id.imageSpace_imageView);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) findViewById(R.id.imageSpace_stepComplete);

                if(button.isEnabled()){
                    stepRedoValidation("Are you sure you want replace the image?");
                }
                else{
                    mImageView.setImageResource(gri());
                }
                enableBtn(button);
            }
        });
        mImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        final ImageView audioBtn = (ImageView) findViewById(R.id.audioSpace_imageView);
        audioBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) findViewById(R.id.audioSpace_stepComplete);
                if(button.isEnabled()){
                    stepRedoValidation("Are you sure you want to replace the audio?");
                }
                else {
                    audioBtn.setImageResource(gfa());
                }
                enableBtn(button);
            }
        });
        audioBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().setColorFilter(0x77000000, PorterDuff.Mode.SRC_ATOP);
                        view.invalidate();
                        break;
                    }
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL: {
                        ImageView view = (ImageView) v;
                        view.getDrawable().clearColorFilter();
                        view.invalidate();
                        break;
                    }
                }
                return false;
            }
        });

        final EditText step_text = (EditText) findViewById(R.id.textSpace_editText);

        step_text.addTextChangedListener(new TextWatcher() {
            String text;

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                text = step_text.getText().toString();
                Button button = (Button) findViewById(R.id.textSpace_stepComplete);

                if (text.trim().length() == 0) {
                    if (button.isEnabled()) {
                        button.setClickable(false);
                        button.setEnabled(false);
                    }
                } else {
                    if (!button.isEnabled()) {
                        button.setClickable(true);
                        button.setEnabled(true);
                    }
                }
            }
        });
    }

    private void initTabs() {

        TabHost tabHost = (TabHost) findViewById(R.id.tabHost);
        tabHost.setup();

        TabHost.TabSpec tabSpec = tabHost.newTabSpec("tag1");
        tabSpec.setContent(R.id.text_tab);
        tabSpec.setIndicator("Text");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        tabSpec.setContent(R.id.audio_tab);
        tabSpec.setIndicator("Audio");
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        tabSpec.setContent(R.id.image_tab);
        tabSpec.setIndicator("Image");
        tabHost.addTab(tabSpec);


        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                if (s.equals("tag2")) {

                }
            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void stepRedoValidation(String message) {

        AlertDialog.Builder builder = new AlertDialog.Builder(BuildStepActivity.this);
        builder.setMessage(message);
        builder.setTitle("Confirm replacement");
        builder.setCancelable(false);

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                final ImageView mImageView = (ImageView) findViewById(R.id.imageSpace_imageView);
                mImageView.setImageResource(gri());
                dialog.cancel();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();

    }

}
