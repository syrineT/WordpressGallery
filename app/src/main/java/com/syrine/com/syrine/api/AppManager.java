package com.syrine.com.syrine.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import com.syrine.MainActivity;
import com.syrine.WordPressWebview;
import com.syrine.com.syrine.model.ToknModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.MultipartTypedOutput;
import retrofit.mime.TypedFile;

/**
 * Created by syrinetrabelsi on 25/01/15.
 */
public class AppManager {
    public static void uploadPic(Context context, Bitmap bitmap, String token) throws IOException {
        final ProgressDialog dialog = ProgressDialog.show(context, "", "Chargement", true);
        MultipartTypedOutput mto = new MultipartTypedOutput();
        File dest = new File(Environment.getExternalStorageDirectory(), UUID.randomUUID() + ".jpg");
        FileOutputStream out = new FileOutputStream(dest);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, out);
        mto.addPart("media[]", new TypedFile("image/jpg", dest));


        RetrofitService.getService().uploadPic(token, mto, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                dialog.dismiss();
            }

            @Override
            public void failure(RetrofitError error) {
                dialog.dismiss();
            }
        });
    }

    public static void requestToken(final MainActivity context) {
        RetrofitService.getService().requestToken("39109", "oauth://login.syrine.com", "code", "https://syrine23.wordpress.com/", new Callback<Response>() {
            @Override
            public void success(Response result, Response response) {

                BufferedReader reader = null;
                StringBuilder sb = new StringBuilder();
                try {

                    reader = new BufferedReader(new InputStreamReader(result.getBody().in()));

                    String line;

                    try {
                        while ((line = reader.readLine()) != null) {
                            sb.append(line);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Intent i = new Intent(context, WordPressWebview.class);
                i.putExtra("content", sb.toString());
                context.startActivityForResult(i, 568);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

  /*  private static void saveToken(MainActivity context, String token) {
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString("token", token);
        edit.apply();
    }

    public static String getSavedToken(MainActivity context) {
        SharedPreferences sharedPref = context.getPreferences(Context.MODE_PRIVATE);
        return sharedPref.getString("token", null);

    }*/

    public static void getToken(final MainActivity context, String code, final Bitmap imageBitmap) {
        RetrofitService.getService().getToken("39109", "oauth://login.syrine.com", "IugJWGVukPSvdpIpqeuWBTiFcKxypg2Y9bKMJLsYOOtxgaB9cCjM9rJ6R9hhIisk", code, "authorization_code", new Callback<ToknModel>() {
            @Override
            public void success(ToknModel s, Response response) {
                //saveToken(context, s.getAccess_token());
                try {
                    AppManager.uploadPic(context, imageBitmap, "Bearer " + s.getAccess_token());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public static void dispatchTakePictureIntent(MainActivity context) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivityForResult(takePictureIntent, MainActivity.REQUEST_IMAGE_CAPTURE);
        }
    }
}
