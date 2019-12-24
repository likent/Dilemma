package com.cmp.dilemma.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.cmp.dilemma.R;
import com.dgreenhalgh.android.simpleitemdecoration.grid.GridDividerItemDecoration;


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


