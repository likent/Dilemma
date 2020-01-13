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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmp.GridItem;
import com.cmp.Profile.ProfileActivity;
import com.cmp.Setting.SettingActivity;
import com.cmp.Utils.AutoFitGridLayout;
import com.cmp.develop.R;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;

    ArrayList<ArrayList<GridItem>> gridItems;
    AutoFitGridLayout autoFitGridLayout;
    ImageButton btnProfile, btnSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnProfile = findViewById(R.id.btnProfile);
        btnSettings = findViewById(R.id.btnSetting);
        btnProfile.setOnClickListener(this);
        btnSettings.setOnClickListener(this);

//        Spinner spinner = findViewById(R.id.spinnerType);
//        String[] arraySpinner = new String[] {
//                "Type"
//        };
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item, arraySpinner);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);

        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Random random = new Random();
        gridItems = new ArrayList<ArrayList<GridItem>>();
        autoFitGridLayout = findViewById(R.id.autofit);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i = 0; i < 13; i++){
            int randGridItems = random.nextInt(5)+1;
            gridItems.add(new ArrayList<GridItem>());
            for(int j = 0; j < randGridItems; j++){
                View v = inflater.inflate(R.layout.item_grid, autoFitGridLayout, false);
                gridItems.get(i).add(new GridItem(v, R.mipmap.test_phone_1, j));
            }
        }


        recyclerView.setAdapter(new RVAdapter(gridItems));

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

    ArrayList<ArrayList<GridItem>> adapterItemList;

    public RVAdapter(ArrayList<ArrayList<GridItem>> items){
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
        AutoFitGridLayout autoFitGridLayout;

        public HeaderHolder(@NonNull View itemView) {
            super(itemView);
            layoutInflater = LayoutInflater.from(itemView.getContext());
           // autoFitGridLayout = itemView.findViewById(R.id.autofit);
           // View view = layoutInflater.inflate(R.layout.header, autoFitGridLayout, false);
           // autoFitGridLayout.addView(view);
        }
    }

    class DilemmaItemsHolder extends RecyclerView.ViewHolder  {

        AutoFitGridLayout autoFitGridLayout;
        LayoutInflater inflater;
        ArrayList<GridItem> itemGridItems;

        public DilemmaItemsHolder(@NonNull View itemView) {
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