package com.pulkit4tech.popularmovies1.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.pulkit4tech.popularmovies1.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by PULKIT on 2/12/2016.
 */
public class ImageAdapter extends BaseAdapter {



    private Context mContext;
    ArrayList<String> images = new ArrayList<>();
    public ImageAdapter(Context c) {
        mContext = c;

    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    mContext.getResources().getDimensionPixelSize(R.dimen.image_height)));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setPadding(mContext.getResources().getDimensionPixelSize(R.dimen.image_padding),
                    mContext.getResources().getDimensionPixelSize(R.dimen.image_padding),
                    mContext.getResources().getDimensionPixelSize(R.dimen.image_padding),
                    mContext.getResources().getDimensionPixelSize(R.dimen.image_padding));

        } else {
            imageView = (ImageView) convertView;
        }



        Picasso.with(mContext).load(images.get(position)).placeholder(R.drawable.placeholder).error(R.drawable.ic_error_black_48dp).into(imageView);


        return imageView;
    }


    public void add_images(String img){
        images.add(img);
    }

    public void clear_images(){
        images.clear();
    }

}
