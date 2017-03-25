package edu.pitt.cs.cs1635.cookingbuddy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import java.io.File;
import java.util.Calendar;

/**
 * Created by chevy on 3/15/17.
 */

public class MyCameraService extends AppCompatActivity {

    private static final int TAKE_PICTURE = 1;
    private Uri imageUri;
    private View imageComponent;
    
    public View takePhoto() {

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String timestamp = (Calendar.getInstance().getTimeInMillis() + ".jpg");
        File photo = new File(Environment.getExternalStorageDirectory(),  timestamp);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        imageUri = Uri.fromFile(photo);
        startActivityForResult(intent, TAKE_PICTURE);

        return imageComponent;
    }

    private View FrameImage(Uri uri){

        LinearLayout linearLayout= new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));


        Bitmap bitmap = null;
        try {
            getContentResolver().notifyChange(uri, null);
            bitmap = android.provider.MediaStore.Images.Media.getBitmap(
                    getContentResolver(), uri
            );
        } catch (Exception e) {
            Log.e("Camera", e.getMessage());
        }

        ImageView imageView = new ImageView(this);
        imageView.setImageBitmap(bitmap);
        imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT));

        linearLayout.addView(imageView);
        setContentView(linearLayout);

        return linearLayout;
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


//            case Flashum.TAKE_MUSIC:
//            case Flashum.TAKE_VOICE:
//                if (resultCode == Activity.RESULT_OK)
//                {
//                    Log.i(Flashum.LOG_TAG, "onActivityResult got new music");
//                    Bundle extras = data.getExtras();
//                    try {
//                        Uri u = data.getData();
//                        String imageUri;
//                        try {
//                            imageUri = getRealPathFromURI(u);
//                        } catch (Exception ex) {
//                            imageUri = u.getPath();
//                        }
//                        File file = new File(imageUri);
//                        FragmentFlash fragmentFlash = (FragmentFlash)mTabsAdapter.getFragment("flash");
//                        if (fragmentFlash != null)
//                            fragmentFlash.gotMusic(file.getPath());
//                    } catch (Exception ex) {
//                        String s = ex.toString();
//                        Log.i(Flashum.LOG_TAG, "onActivityResult " + s);
//                    }
//                }
//                else
//                {
//                    Log.i(Flashum.LOG_TAG, "onActivityResult Failed to get music");
//                }
//                break;
        }
    }

//    public String getRealPathFromURI(Uri contentUri) {
//        String[] proj = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(contentUri, proj, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }

