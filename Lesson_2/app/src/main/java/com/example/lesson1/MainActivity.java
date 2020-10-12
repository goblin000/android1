package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnSelectTown = findViewById(R.id.btnSelectTown);
        btnSelectTown.setOnClickListener(btnClick);

        selectTown("Бильбао");


        Animation anim = null;
        anim = AnimationUtils.loadAnimation(this, R.anim.animation);

        TextView textView = findViewById(R.id.textTownName);
        textView.startAnimation(anim);
    }


    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
//            intent.putExtra("city", "bilbao");
            startActivity(intent);
        }
    };




    private void selectTown(final String townName){
        TextView textView = findViewById(R.id.textTownName);
        textView.setText(townName);
    }







//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btnSelectTown:
//                Toast.makeText(this, "Действие на кнопку не задано", Toast.LENGTH_SHORT).show();
//                break;
//        }
//    }

}