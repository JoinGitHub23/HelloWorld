package com.example.helloworld;

public class Passenger {
    private Double lat;
    private Double lng;
    private String name;

    public Passenger(Double lat, Double lng, String name){
        this.lat = lat;
        this.lng = lng;
        this.name = name;

    }

    public Double getLat() {
        return lat;
    }

    public Double getLng() {
        return lng;
    }

    public String getName() {
        return name;
    }
}
