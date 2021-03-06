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

        void onFailure(Throwable t);
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

            Bitmap bitmap = null;
            try {
                URL url = new URL(strings[0]);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();

                InputStream in = conn.getInputStream();

                bitmap = BitmapFactory.decodeStream(in);
                in.close();

            } catch (IOException e) {
                mImageCallback.onFailure(e);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mImageCallback.onSuccess(bitmap);
        }
    }
}
