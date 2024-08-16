package com.example.pokedex;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.URL;

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... urls) {
        String url = urls[0];
        Bitmap bitmap = null;
        try {
            InputStream inputStream = new URL(url).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }


}
