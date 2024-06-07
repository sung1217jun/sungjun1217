package com.example.dcu_image_viwer;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private HorizontalScrollView horizontalScrollView;
    private DetailImageAdapter detailImageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        horizontalScrollView = findViewById(R.id.horizontalScrollView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            detailImageAdapter = new DetailImageAdapter(this);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            horizontalScrollView.setOnCapturedPointerListener(detailImageAdapter);
        }

        int position = getIntent().getIntExtra("position", 0);
        int resourceId = getResources().getIdentifier("natural" + (position + 1), "drawable", getPackageName());

        // Load and scale down the image
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), resourceId, options);

        // Calculate the sample size
        options.inSampleSize = calculateInSampleSize(options, imageView.getWidth(), imageView.getHeight());
        options.inJustDecodeBounds = false;

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resourceId, options);
        imageView.setImageBitmap(bitmap);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight && (halfWidth / inSampleSize) >= reqWidth) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private class DetailImageAdapter extends com.example.dcu_image_viwer.DetailImageAdapter {
        public DetailImageAdapter(DetailActivity detailActivity) {
            super(detailActivity);
        }

        @Override
        public boolean onCapturedPointer(View view, MotionEvent event) {
            return false;
        }
    }
}
