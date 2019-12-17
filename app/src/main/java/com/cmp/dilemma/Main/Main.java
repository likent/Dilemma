package com.cmp.dilemma.Main;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.cmp.dilemma.R;

import java.util.ArrayList;

public class Main extends AppCompatActivity {

    Spinner s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayList<String> arraySpinner = new ArrayList<String>();
        arraySpinner.add("TYPE");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s = findViewById(R.id.spinnerType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
    }
}
