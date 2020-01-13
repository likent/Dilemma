package com.cmp.Setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;

import com.cmp.Profile.ProfileActivity;
import com.cmp.develop.R;
import com.cmp.develop.activities.MainActivity;

public class SettingActivity extends Activity implements View.OnClickListener {

    ImageButton btnProfile, btnHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        btnHome = findViewById(R.id.btnHome);
        btnProfile = findViewById(R.id.btnProfile);
        btnHome.setOnClickListener(this);
        btnProfile.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if(v.getId() == btnHome.getId()){
            intent = new Intent(SettingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }else if(v.getId() == btnProfile.getId()){
            intent = new Intent(SettingActivity.this, ProfileActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
