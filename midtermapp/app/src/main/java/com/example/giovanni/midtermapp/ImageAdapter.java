package com.example.giovanni.midtermapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends PagerAdapter {
    private Context mContext;
    private int[] imgArray = new int[] {R.drawable.img1,R.drawable.img2};
    ImageAdapter(Context context)
    {
        mContext=context;
    }

    @Override
    public int getCount() {
        return imgArray.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        // imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(imgArray[position]);
        container.addView(imageView);
        return imageView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ImageView)object);
    }
}
