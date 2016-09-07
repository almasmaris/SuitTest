package com.id.maris.suittest.model;

import android.widget.ImageView;

import com.id.maris.suittest.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;


/**
 * Created by almas on 06-Sep-16.
 */

public class GuestModel implements Serializable {
    private int id;
    private String name;
    private String birthdate;
    private String image_path;

    public void loadImage(String path, ImageView imageView){
        //dummy image
        Picasso.with(imageView.getContext()).load(R.drawable.avatar).into(imageView);
    }

    public void getImage(ImageView imageView) {
        loadImage(image_path, imageView);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
