package edu.pitt.cs.cs1635.cookingbuddy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.Calendar;

/**
 * Created by chevy on 3/15/17.
 */

public class MyCameraService extends BuildStepActivity {

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    private Bitmap imageComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("HERE", "HERE");
        takePhoto();
        finish();
    }


    public Bitmap takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String timestamp = (Calendar.getInstance().getTimeInMillis() + ".jpg");
        File photo = new File(Environment.getExternalStorageDirectory(),  timestamp);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        Log.d("INTENT", intent.toString());
        startActivityForResult(intent, TAKE_PICTURE);

        BuildStepActivity.setCurrentImage(imageComponent);
        return imageComponent;
    }

    private Bitmap FrameImage(Uri uri){

        Bitmap bitmap = null;
        try {
            getContentResolver().notifyChange(uri, null);
            bitmap = MediaStore.Images.Media.getBitmap(
                    getContentResolver(), uri
            );
        } catch (Exception e) {
            Log.e("Camera", e.getMessage());
        }

        return bitmap;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case TAKE_PICTURE: {
                if (resultCode == Activity.RESULT_OK) {
                    imageComponent = FrameImage(imageUri);
                    }
                }
                break;
            }
        }
    }

//    public String getRealPathFromURI(Uri contentUri) {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }

