package com.cmp.develop.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cmp.develop.R;
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        GridLayout gridLayout = findViewById(R.id.image_conteiner);
        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.divider_line);
        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.divider_line);

        recyclerView.addItemDecoration(new GridDividerItemDecoration(horizontalDivider, verticalDivider, 4));
        Drawable phone1 = ContextCompat.getDrawable(this, R.mipmap.test_phone_1);
        Drawable phone2 = ContextCompat.getDrawable(this, R.mipmap.test_phone_2);
    }
}

class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVHolder>{
    ArrayList<Drawable> images;

    public RVAdapter(ArrayList<Drawable> images){
        this.images = images;
    }

    @NonNull
    @Override
    public RVHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View gridLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_rv_item, parent, false);
        return new RVHolder(gridLayout);

    }

    @Override
    public void onBindViewHolder(@NonNull RVHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    class RVHolder extends RecyclerView.ViewHolder{

        public RVHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}