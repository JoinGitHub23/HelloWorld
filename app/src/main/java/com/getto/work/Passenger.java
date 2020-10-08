package com.getto.work;


public class Passenger {

    private String name;
    private String email;
    private String password;
    private String phone;
//    private Map<Long, Long> workLocation;
//    private Map<Long, Long> homeLocation;
    private com.getto.work.Point workLocation;
    private com.getto.work.Point homeLocation;


    public Passenger(String name, String email, String password, String phone, Point workLocation, Point homeLocation) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.workLocation = workLocation;
        this.homeLocation = homeLocation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Point getWorkLocation() {
        return workLocation;
    }

    public void setWorkLocation(Point workLocation) {
        this.workLocation = workLocation;
    }

    public Point getHomeLocation() {
        return homeLocation;
    }

    public void setHomeLocation(Point homeLocation) {
        this.homeLocation = homeLocation;
    }
}
