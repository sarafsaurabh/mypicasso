package com.self.mypicasso;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.self.mypicasso.MyPicassoLibrary.ImageCallback;


public class MyPicassoActivity extends AppCompatActivity {

    private static final String TAG = MyPicassoActivity.class.getSimpleName();

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_picasso);

        String url = "http://c1.staticflickr.com/8/7039/27446874495_20034ceb85_z.jpg";

        mImageView = (ImageView) findViewById(R.id.imageView);

        MyPicassoLibrary.load(url, new ImageCallback() {

            @Override
            public void onSuccess(Bitmap bitmap) {
                mImageView.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e(TAG, "Error downloading image: " + Log.getStackTraceString(t));
            }
        });
    }
}
