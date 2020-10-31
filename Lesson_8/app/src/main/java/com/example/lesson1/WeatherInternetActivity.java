package com.example.lesson1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class WeatherInternetActivity extends AppCompatActivity {

    private EditText city;
    private EditText temperature;
    private EditText temperature_max;
    private EditText temperature_min;
    private EditText pressure;
    private EditText humidity;
    private EditText windSpeed;
    private EditText townSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_internet);
        init();
    }
    private void init() {
        city = findViewById(R.id.textCity);
        temperature = findViewById(R.id.textTemprature);
        temperature_max = findViewById(R.id.textTempratureMax);
        temperature_min = findViewById(R.id.textTempratureMin);
        pressure = findViewById(R.id.textPressure);
        humidity = findViewById(R.id.textHumidity);
        windSpeed = findViewById(R.id.textWindspeed);
        townSearch = findViewById(R.id.newTown);

        Button refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(clickListener);
    }



    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            new CL_GetDataFromServer(townSearch.getText().toString(), city, temperature, temperature_max, temperature_min, pressure, humidity, windSpeed);
        }
    };
}