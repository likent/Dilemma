package com.cmp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

public class GridItem {
    private View view;
    private int position;
    private int percent;
    private Drawable image;


    public GridItem(View v, Bitmap img, int position){
        view = v;
        image = new BitmapDrawable(v.getResources(), img);
    }

    public GridItem(View v, Drawable d, int position){
        view = v;
        image = d;
    }

    public GridItem(View v, int imageResource, int position){
        view = v;
        image = view.getContext().getDrawable(imageResource);
    }

    public View getView() {
        return view;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
