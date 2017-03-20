package com.self.mypicasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class MyPicassoNetworkException {

    public interface ImageCallback {
        void onSuccess(Bitmap bitmap);

        void onFailure(Throwable t);
    }

    public static void load(String stringUrl, ImageCallback callback) {

        Bitmap bitmap = null;
        try {
            URL url = new URL(stringUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();

            InputStream in = conn.getInputStream();

            bitmap = BitmapFactory.decodeStream(in);
            in.close();
            callback.onSuccess(bitmap);

        } catch (IOException e) {
            callback.onFailure(e);
        }
    }
}
