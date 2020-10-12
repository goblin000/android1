package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private ImageView imageView_city;
    final SaveParamSingleton saveParamSingleton = SaveParamSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textTownName);
        imageView_city = findViewById(R.id.imageView);
        Button btnSelectTown = findViewById(R.id.btnSelectTown);
        btnSelectTown.setOnClickListener(btnClick);

        selectTown();

        Animation anim = null;
        anim = AnimationUtils.loadAnimation(this, R.anim.animation);

        TextView textView = findViewById(R.id.textTownName);
        textView.startAnimation(anim);
    }






    private View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(MainActivity.this, SelectCityActivity.class);
            startActivityForResult (intent,1);
        }
    };




    private void selectTown(){

        String townName = saveParamSingleton.getCityName();
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



//    @Override
//    protected void onSaveInstanceState(Bundle saveInstanceState){
//        super.onSaveInstanceState(saveInstanceState);
////        Toast.makeText(getApplicationContext(), "onSaveInstanceState()", Toast.LENGTH_SHORT).show();
//        saveInstanceState.putString( "town", ((TextView)findViewById(R.id.textTownName)).getText().toString() );
////        saveInstanceState.put putInt( "imageView_city", (Integer)findViewById(R.id.imageView).getTag() );
////        saveInstanceState.put
//
////        Integer dr = imageView_city.getDrawable().getAlpha();
////        Toast.makeText(getApplicationContext(), imageView_city.getDrawable().toString(), Toast.LENGTH_LONG).show();
////        imageView_city.setImageDrawable( dr.getClass(). );
////        imageView_city.getDrawable().toString()
////        saveInstanceState.putInt( "imageTown", 33 );
////        integer_imageView_city = (Integer)imageView_city.getTag();
////        integer_imageView_city = integer_imageView_city == null ? 0 : integer_imageView_city;
//    }

//    @Override
//    protected void onRestoreInstanceState(Bundle saveInstanceState){
//        super.onRestoreInstanceState(saveInstanceState);
//        ((TextView)findViewById(R.id.textTownName)).setText(saveInstanceState.getString("town"));
////        imageView_city.setImageResource(integer_imageView_city);
////        imageView_city.setImageResource(saveInstanceState.getInt("imageTown"));
////        selectTown(saveInstanceState.getString("town"), saveInstanceState.getInt("imageTown"));
//    }




}