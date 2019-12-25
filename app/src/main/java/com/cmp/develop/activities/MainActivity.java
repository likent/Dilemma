package com.cmp.develop.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cmp.develop.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinnerType);
        String[] arraySpinner = new String[] {
                "Type"
        };
        Spinner s = (Spinner) findViewById(R.id.spinnerType);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RVAdapter());

//        GridLayout gridLayout = findViewById(R.id.image_conteiner);
//        Drawable horizontalDivider = ContextCompat.getDrawable(this, R.drawable.divider_line);
//        Drawable verticalDivider = ContextCompat.getDrawable(this, R.drawable.divider_line);
        

       // recyclerView.addItemDecoration(new GridDividerItemDecoration(horizontalDivider, verticalDivider, 4));
//        Drawable phone1 = ContextCompat.getDrawable(this, R.mipmap.test_phone_1);
//        Drawable phone2 = ContextCompat.getDrawable(this, R.mipmap.test_phone_2);
    }
}

class RVAdapter extends RecyclerView.Adapter<RVAdapter.RVHolder>{

    public RVAdapter(){

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
        return 2;
    }

    class RVHolder extends RecyclerView.ViewHolder{

        public RVHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}