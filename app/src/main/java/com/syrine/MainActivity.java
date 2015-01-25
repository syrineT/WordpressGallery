package com.syrine;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.syrine.com.syrine.api.AppManager;

import java.io.IOException;


public class MainActivity extends Activity implements View.OnClickListener {
    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final int REQUEST_TOKEN = 568;

    private Button takePic;
    private ImageView pic;
    private Bitmap imageBitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        takePic = (Button) findViewById(R.id.button);
        pic = (ImageView) findViewById(R.id.imageView);
        takePic.setOnClickListener(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");
            pic.setImageBitmap(imageBitmap);
          /*  if (AppManager.getSavedToken(this) != null) {
                try {
                    AppManager.uploadPic(this, imageBitmap, AppManager.getSavedToken(this));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {*/
            AppManager.requestToken(this);
            //  }


        } else if (requestCode == REQUEST_TOKEN && resultCode == RESULT_OK) {
            String code = (String) data.getExtras().get("code");
            if (code != null) {
                AppManager.getToken(this, code, imageBitmap);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == takePic) {
            AppManager.dispatchTakePictureIntent(this);
        }
    }
}
