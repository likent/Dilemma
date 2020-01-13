package com.cmp.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.cmp.CreateDilemma.CreateDilemmaActivity;
import com.cmp.GridItem;
import com.cmp.Setting.SettingActivity;
import com.cmp.Utils.AutoFitGridLayout;
import com.cmp.develop.R;
import com.cmp.develop.activities.MainActivity;

import java.util.ArrayList;
import java.util.Random;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton btnHome, btnSettings;
    Button btnCreateDilemma;
    RecyclerView recyclerView;
    ArrayList<ArrayList<GridItem>> createdDillemList;
    AutoFitGridLayout autoFitGridLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnHome = findViewById(R.id.btnHome);
        btnSettings = findViewById(R.id.btnSetting);
        btnCreateDilemma = findViewById(R.id.btn_profile_addDilemma);
        btnCreateDilemma.setOnClickListener(this);
        btnHome.setOnClickListener(this);
        btnSettings.setOnClickListener(this);
        recyclerView = findViewById(R.id.recyclerView_profile);
        autoFitGridLayout = findViewById(R.id.autofit);

        createdDillemList = new ArrayList<>();
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        Random random = new Random();
        for(int i = 0; i < 13; i++){
            int randGridItems = random.nextInt(5)+1;
            createdDillemList.add(new ArrayList<GridItem>());
            for(int j = 0; j < randGridItems; j++){
                View v = inflater.inflate(R.layout.item_grid, autoFitGridLayout, false);
                createdDillemList.get(i).add(new GridItem(v, R.mipmap.test_phone_1, j));
            }
        }

        recyclerView.setAdapter(new ProfileRVAdapter(createdDillemList));
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        if(v.getId() == btnSettings.getId()){
            intent = new Intent(ProfileActivity.this, SettingActivity.class);
            startActivity(intent);
            finish();
        }else if(v.getId() == btnHome.getId()){
            intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(v.getId() == btnCreateDilemma.getId()){
            intent = new Intent(ProfileActivity.this, CreateDilemmaActivity.class);
            startActivity(intent);
        }
    }

    class ProfileRVAdapter extends RecyclerView.Adapter{

        ArrayList<ArrayList<GridItem>> publishedItems;

        public ProfileRVAdapter(ArrayList<ArrayList<GridItem>> publishedItems){
            this.publishedItems = publishedItems;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_item, parent, false);
            return new ProfileRVHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((ProfileRVHolder) holder).bind(publishedItems.get(position));
        }

        @Override
        public int getItemCount() {
            return publishedItems.size();
        }

        class ProfileRVHolder extends RecyclerView.ViewHolder{

            ArrayList<GridItem> itemGridItems;
            LayoutInflater inflater;
            AutoFitGridLayout autoFitGridLayout;

            public ProfileRVHolder(@NonNull View itemView) {
                super(itemView);
                autoFitGridLayout = itemView.findViewById(R.id.autofit);
                inflater = LayoutInflater.from(itemView.getContext());
            }

            public void bind(ArrayList<GridItem> itemGridItems){
                this.itemGridItems = itemGridItems;
                autoFitGridLayout.removeAllViews();
                for(int i = 0; i < itemGridItems.size(); i++){
                    View v = inflater.inflate(R.layout.item_grid, autoFitGridLayout , false);
                    TextView textView = v.findViewById(R.id.item_tv_percent);
                    ImageView imageView = v.findViewById(R.id.item_img);
                    textView.setText(itemGridItems.get(i).getPercent() + "%");
                    imageView.setImageDrawable(itemGridItems.get(i).getImage());
                    autoFitGridLayout.addView(v);
                }
                autoFitGridLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        TextView textView = v.findViewById(R.id.item_tv_percent);
                        textView.setText(100 + "%");
                        //Toast.makeText(v.getContext(), ) autoFitGridLayout.indexOfChild(v);

                    }
                });
            }
        }
    }
}
