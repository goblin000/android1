package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView_city;
    private String townName;
    final SaveParamSingleton saveParamSingleton = SaveParamSingleton.getInstance();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textTownName);
        imageView_city = findViewById(R.id.imageView);
        Button btnSelectTown = findViewById(R.id.btnSelectTown);
        Button btnOpenCityInfo = findViewById(R.id.btnOpenInfoAboutCity);

        btnSelectTown.setOnClickListener(btnClick);
        btnOpenCityInfo.setOnClickListener(btnClick);


// при открытии отображаем город по умолчанию
        selectTown();
        townName = getString(R.string.selectActivity_btn_city0);


// создаём и запускаем анимацию названия города
        Animation anim = null;
        anim = AnimationUtils.loadAnimation(this, R.anim.animation);

        TextView textView = findViewById(R.id.textTownName);
        textView.startAnimation(anim);
    }






    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.btnSelectTown:
                        Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
                        startActivityForResult (intent,1);
                    break;

                case R.id.btnOpenInfoAboutCity:

                    String string = String.format("https://%s.wikipedia.org/wiki/%s", Locale.getDefault().getLanguage(), townName);
                    Uri uri = Uri.parse( string );
                        Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(browser);
                    break;
            }
        }
    };




    private void selectTown(){

        townName = saveParamSingleton.getCityName();
        if(!townName.isEmpty()){
            imageView_city.setImageResource( saveParamSingleton.getCityImage() );
            textView.setText(townName);
        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {return;}

        saveParamSingleton.setCityName(data.getStringExtra("name"));
        saveParamSingleton.setCityImage(data.getIntExtra("nameImage", -1));

        selectTown();
    }


}