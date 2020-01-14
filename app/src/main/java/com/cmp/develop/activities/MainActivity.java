package com.cmp.develop.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmp.DilemmaItem;
import com.cmp.GridItem;
import com.cmp.Profile.ProfileActivity;
import com.cmp.Setting.SettingActivity;
import com.cmp.Utils.AutoFitGridLayout;
import com.cmp.develop.R;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;

    ArrayList<DilemmaItem> recyclerViewItems;
    AutoFitGridLayout autoFitGridLayout;
    ImageButton btnProfile, btnSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        autoFitGridLayout = findViewById(R.id.main_rv_item_autofitGridLayout);
        btnProfile = findViewById(R.id.btnProfile);
        btnSettings = findViewById(R.id.btnSetting);

        btnProfile.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        Spinner spinner = findViewById(R.id.spinnerType);
//        String[] arraySpinner = new String[] {
//                "Type"
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, arraySpinner);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);




        Random random = new Random();
        recyclerViewItems = new ArrayList<DilemmaItem>();
        ArrayList<GridItem> testGridItems;
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        for(int i = 0; i < 13; i++){
            testGridItems = new ArrayList<>();
            int randGridItems = random.nextInt(5)+1;
            for(int j = 0; j < randGridItems; j++){
                View v = inflater.inflate(R.layout.item_grid, autoFitGridLayout, false);
                testGridItems.add(new GridItem(v, R.mipmap.test_phone_1, j));
            }
            recyclerViewItems.add(new DilemmaItem(this, "Choose one", UUID.randomUUID(), testGridItems));
        }

        recyclerView.setAdapter(new RVAdapter(recyclerViewItems));

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v.getId() == btnSettings.getId()){
            intent = new Intent(MainActivity.this, SettingActivity.class);
            startActivity(intent);
            finish();
        }else if(v.getId() == btnProfile.getId()){
            intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

class RVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    public final int HEADER = Integer.MAX_VALUE;

    ArrayList<DilemmaItem> adapterItemList;

    public RVAdapter(ArrayList<DilemmaItem> items){
        this.adapterItemList = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            return new HeaderHolder(view);
        }
        else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_item, parent, false);

            return new DilemmaItemsHolder(view);
        }
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() != HEADER)
            ((DilemmaItemsHolder)holder).bind(adapterItemList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0) return HEADER;
        else return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return adapterItemList.size();
    }


    class HeaderHolder extends RecyclerView.ViewHolder{
        LayoutInflater layoutInflater;

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            layoutInflater = LayoutInflater.from(itemView.getContext());
        }
    }

    class DilemmaItemsHolder extends RecyclerView.ViewHolder  {

        AutoFitGridLayout autoFitGridLayout;
        LayoutInflater inflater;
        DilemmaItem itemGridItems;
        TextView textLeft, textRight;
        ImageView profilePhoto;

        public DilemmaItemsHolder(@NonNull View itemView) {
            super(itemView);
            autoFitGridLayout = itemView.findViewById(R.id.main_rv_item_autofitGridLayout);
            inflater = LayoutInflater.from(itemView.getContext());
            textLeft = itemView.findViewById(R.id.main_rv_item_left_text);
            textRight = itemView.findViewById(R.id.main_rv_item_right_text);
            profilePhoto = itemView.findViewById(R.id.main_rv_item_profile_image);
        }

        public void bind(final DilemmaItem dilemmaItem){
            this.itemGridItems = dilemmaItem;
            String[] textWords = dilemmaItem.getItemText().split(" ");
            textRight.setText("");
            textLeft.setText("");
            for(int i = 0; i < textWords.length; i++){
                if(textWords.length / 2 < i)
                    textLeft.setText(textLeft.getText() + " " + textWords[i]);
                else
                    textRight.setText(textRight.getText() + " " + textWords[i]);
            }
            profilePhoto.setImageBitmap(dilemmaItem.getUserImage());

            autoFitGridLayout.removeAllViews();
            for(int i = 0; i < dilemmaItem.getItemsList().size(); i++){
                View v =  dilemmaItem.getItemsList().get(i).getView();
                TextView textView = v.findViewById(R.id.item_tv_percent);
                ImageView imageView = v.findViewById(R.id.item_img);
                textView.setText(dilemmaItem.getItemsList().get(i).getPercent() + "%");
                imageView.setImageDrawable(dilemmaItem.getItemsList().get(i).getImage());
                autoFitGridLayout.addView(v);
            }
            autoFitGridLayout.refreshNotGoneChildList();
            autoFitGridLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int i = 0; i < dilemmaItem.getItemsList().size(); i++){
                        if(dilemmaItem.getItemsList().get(i).getView()== v){
                            Toast.makeText(v.getContext(), "Found at position " + i, Toast.LENGTH_LONG).show();
                            dilemmaItem.getItemsList().get(i).setPercent(dilemmaItem.getItemsList().get(i).getPercent() + 10);
                        }
                    }


                }
            });
        }


    }
}