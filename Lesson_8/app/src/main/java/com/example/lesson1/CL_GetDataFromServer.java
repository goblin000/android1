package com.example.lesson1;

import android.os.Handler;
import android.util.Log;
import android.widget.EditText;


import com.example.lesson1.model.WeatherRequest;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class CL_GetDataFromServer {

    private static final String TAG = "MY_LOG";
    private static final String WEATHER_URL = "https://api.openweathermap.org/data/2.5/weather?q=%s&APPID=%s";
    private static final String WEATHER_API_KEY = "@@@@@@@@@@@@@@@@@@@";


    private EditText editText_cityName;
    private EditText editText_temperature;
    private EditText editText_temperatureMax;
    private EditText editText_temperatureMin;
    private EditText editText_pressure;
    private EditText editText_humidity;
    private EditText editText_wind;

    public CL_GetDataFromServer(
            String cityName_search,
            EditText editText_cityName,
            EditText editText_temperature,
            EditText editText_temperatureMax,
            EditText editText_temperatureMin,
            EditText editText_pressure,
            EditText editText_humidity,
            EditText editText_wind
    ){
        this.editText_cityName = editText_cityName;
        this.editText_temperature = editText_temperature;
        this.editText_temperatureMax = editText_temperatureMax;
        this.editText_temperatureMin = editText_temperatureMin;
        this.editText_pressure = editText_pressure;
        this.editText_humidity = editText_humidity;
        this.editText_wind = editText_wind;

        connect(cityName_search);
    }


    private void connect(String cityName_search){
        try {
            final URL uri = new URL(String.format(WEATHER_URL, cityName_search, WEATHER_API_KEY));

            final Handler handler = new Handler(); // Запоминаем основной поток
            new Thread(new Runnable() {
                public void run() {
                    HttpsURLConnection urlConnection = null;
                    try {
                        urlConnection = (HttpsURLConnection)uri.openConnection();
                        urlConnection.setRequestMethod("GET");

                        urlConnection.setReadTimeout(10000);

                        BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                        String result = getLines(in);


                        Gson gson = new Gson();
                        final WeatherRequest weatherRequest = gson.fromJson(result, WeatherRequest.class);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                displayWeather(weatherRequest);
                            }
                        });
                    } catch (Exception e) {

//                        setSTAT("Такого города нет в базе!");

                        Log.e(TAG, "Fail connection", e);
                        e.printStackTrace();
                    } finally {
                        if (null != urlConnection) {
                            urlConnection.disconnect();
                        }
                    }
                }
            }).start();
        } catch (MalformedURLException e) {
            Log.e(TAG, "Fail URI", e);
            e.printStackTrace();
        }

    }




    private String getLines(BufferedReader in) {
        StringBuffer sb = new StringBuffer(1024);
        String tmp="";
        try{
            while((tmp=in.readLine())!=null)
                sb.append(tmp).append("\n");
            in.close();
        }catch (Exception e){
            Log.e(TAG, "StringBuffer error", e);
        }

        return sb.toString();
    }



    private void displayWeather(WeatherRequest weatherRequest){
        editText_cityName.setText(weatherRequest.getName());
        editText_temperature.setText( String.format("%d", Math.round(weatherRequest.getMain().getTemp()-273.15)) );
        editText_temperatureMax.setText(String.format("%d",Math.round( weatherRequest.getMain().getTemp_max()-273.15)) );
        editText_temperatureMin.setText(String.format("%d", Math.round(weatherRequest.getMain().getTemp_min()-273.15)) );
        editText_pressure.setText(String.format("%d", Math.round(weatherRequest.getMain().getPressure()-273.15)) );
        editText_humidity.setText(String.format("%d", weatherRequest.getMain().getHumidity()));
        editText_wind.setText(String.format("%.1f", weatherRequest.getWind().getSpeed()));
    }

}
