package com.sinhvien.comic.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.sinhvien.comic.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyViewPagerAdapter extends PagerAdapter {
    Context context;
    List<String> imageLinks;
    LayoutInflater layoutInflater;

    public MyViewPagerAdapter(Context context, List<String> imageLinks) {
        this.context = context;
        this.imageLinks = imageLinks;
        layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return imageLinks.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view.equals(o);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View image_layout = layoutInflater.inflate(R.layout.view_pager_item,container,false);

        PhotoView page_image = (PhotoView)image_layout.findViewById(R.id.page_image);
        Picasso.get().load(imageLinks.get(position)).into(page_image);

        container.addView(image_layout);
        return image_layout;
    }
}
