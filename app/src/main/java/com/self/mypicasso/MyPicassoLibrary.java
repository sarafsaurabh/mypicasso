package com.self.mypicasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

class MyPicassoLibrary {

    public interface ImageCallback {
        void onSuccess(Bitmap bitmap);
    }

    public static void load(String stringUrl, ImageCallback callback) {

        DownloadImageTask task = new DownloadImageTask(callback);

        task.execute(stringUrl);

    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        ImageCallback mImageCallback;

        public DownloadImageTask(ImageCallback imageCallback) {
            mImageCallback = imageCallback;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream in = conn.getInputStream();

                Bitmap bitmap = BitmapFactory.decodeStream(in);
                in.close();
                return bitmap;

            } catch (IOException e) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImageCallback.onSuccess(bitmap);
        }
    }
}
