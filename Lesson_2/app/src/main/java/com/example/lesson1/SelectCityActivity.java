package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SelectCityActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

//        Bundle args = getIntent().getExtras();
//        String name = args.get("city").toString();
//        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_city);
        findViewById(R.id.btnTown1).setOnClickListener( btnClick );
        findViewById(R.id.btnTown2).setOnClickListener( btnClick );
        findViewById(R.id.btnTown3).setOnClickListener( btnClick );

    }


    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(SelectCityActivity.this, ((Button)view).getText().toString(), Toast.LENGTH_SHORT).show();
        }
    };

}