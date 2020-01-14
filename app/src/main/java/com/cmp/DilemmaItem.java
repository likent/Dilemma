package com.cmp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.cmp.develop.R;

import java.util.ArrayList;
import java.util.UUID;

public class DilemmaItem {
    private ArrayList<GridItem> itemsList;
    private UUID createdUserID, dilemmaId;
    private Bitmap userImage;
    private String itemText;
    private Context context;

    public DilemmaItem(Context context, String itemText, UUID createdUserID, ArrayList<GridItem> itemsList){
        this.itemText = itemText;
        this.createdUserID = createdUserID;
        this.itemsList = itemsList;
        dilemmaId = UUID.randomUUID();
        this.context = context;
        userImage = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.image_25);
    }

    public DilemmaItem(String itemText, UUID createdUserID, ArrayList<GridItem> itemsList, Bitmap userImage){
        this.itemText = itemText;
        this.createdUserID = createdUserID;
        this.itemsList = itemsList;
        dilemmaId = UUID.randomUUID();
        this.userImage = userImage;
    }

    public ArrayList<GridItem> getItemsList() {
        return itemsList;
    }

    public void setItemsList(ArrayList<GridItem> itemsList) {
        this.itemsList = itemsList;
    }

    public Bitmap getUserImage() {
        return userImage;
    }

    public void setUserImage(Bitmap userImage) {
        this.userImage = userImage;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }
}
