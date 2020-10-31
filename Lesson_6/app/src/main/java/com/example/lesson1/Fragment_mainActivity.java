package com.example.lesson1;

import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.net.sip.SipAudioCall;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Fragment_mainActivity extends Fragment {


    private TextView textView;
    private ImageView imageView_city;
    private String townName;
    final SaveParamSingleton saveParamSingleton = SaveParamSingleton.getInstance();



    public static Fragment_mainActivity newInstance() {
        return new Fragment_mainActivity();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//        View v = inflater.inflate(R.layout.fragment_main_fragment, null);
        View layout = inflater.inflate(R.layout.fragment_main_fragment, container,false);

//        ImageView coatOfArms = new ImageView(getContext());

        textView = layout.findViewById(R.id.textTownName);
        imageView_city = layout.findViewById(R.id.imageView);
        Button btnSelectTown = layout.findViewById(R.id.btnSelectTown);
        Button btnOpenCityInfo = layout.findViewById(R.id.btnOpenInfoAboutCity);
        Button btnAnyDays = layout.findViewById(R.id.btnOpenForAnyDays);



        btnSelectTown.setOnClickListener(btnClick);
        btnOpenCityInfo.setOnClickListener(btnClick);
        btnAnyDays.setOnClickListener(btnClick);


// при открытии отображаем город по умолчанию
        selectTown();
        townName = getString(R.string.selectActivity_btn_city0);


// создаём и запускаем анимацию названия города
        Animation anim = null;
        anim = AnimationUtils.loadAnimation(getContext(), R.anim.animation);

        TextView textView = layout.findViewById(R.id.textTownName);
        textView.startAnimation(anim);

        return layout;
    }






    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }






    public View.OnClickListener btnClick = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            switch (view.getId()){
                case R.id.btnSelectTown:
                    Intent intent = new Intent( getContext(), SelectCityActivity.class);
                    startActivityForResult (intent,1);
                    break;

                case R.id.btnOpenInfoAboutCity:
                    String string = String.format("https://%s.wikipedia.org/wiki/%s", Locale.getDefault().getLanguage(), townName);
                    Uri uri = Uri.parse( string );
                    Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(browser);
                    break;

                case R.id.btnOpenForAnyDays:
                    intent = new Intent( getContext(), RecycViewActivity.class);
                    startActivity(intent);
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {return;}

        saveParamSingleton.setCityName(data.getStringExtra("name"));
        saveParamSingleton.setCityImage(data.getIntExtra("nameImage", -1));

        selectTown();
    }


}