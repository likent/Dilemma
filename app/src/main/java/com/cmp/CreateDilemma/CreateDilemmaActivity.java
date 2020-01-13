package com.cmp.CreateDilemma;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cmp.Profile.ProfileActivity;
import com.cmp.develop.R;

public class CreateDilemmaActivity extends AppCompatActivity implements View.OnClickListener {
    TextView btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dilemma);
        btnBack = findViewById(R.id.tv_btn_createDilemma_back);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == btnBack.getId()){
            Intent intent = new Intent(CreateDilemmaActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
