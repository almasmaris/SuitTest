package com.id.maris.suittest.model;

import android.widget.ImageView;

import com.id.maris.suittest.R;
import com.squareup.picasso.Picasso;

/**
 * Created by almas on 06-Sep-16.
 */

public class EventModel {
    private static final String IMAGE_URL = "http://image.tmdb.org/t/p/w300/";

    private String name;
    private String date;
    private String image_path;

    public void loadImage(String path, ImageView imageView){
        //dummy image
        Picasso.with(imageView.getContext()).load(R.drawable.imageevent).into(imageView);
    }

    public void getImage(ImageView imageView) {
        loadImage(image_path, imageView);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
