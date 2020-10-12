package com.example.lesson1;


public final class SaveParamSingleton {
    private static SaveParamSingleton inst = null;
    private static final Object syncObj = new Object();

    private String cityName;
    private Integer cityImage;

    private SaveParamSingleton(){
        cityName = "";
        cityImage = null;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }


    public void setCityImage(Integer image) {
        cityImage = image;
    }

    public Integer getCityImage() {
        return cityImage;
    }



    public static SaveParamSingleton getInstance(){
        synchronized (syncObj){
            return (inst == null) ? (inst = new SaveParamSingleton()) : inst;
        }
    }
}
