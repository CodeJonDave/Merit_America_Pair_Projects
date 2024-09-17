package com.techelevator.model;

public class Campground {

    private int campgroundId;
    private int parkId;
    private String name;
    private int openFromMonth;
    private int openToMonth;
    private double dailyFee;

    public int getCampgroundId() {
        return campgroundId;
    }

    public void setCampgroundId(int campgroundId) {
        this.campgroundId = campgroundId;
    }

    public int getParkId() {
        return parkId;
    }

    public void setParkId(int parkId) {
        this.parkId = parkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOpenFromMonth() {
        return openFromMonth;
    }

    public void setOpenFromMonth(int openFromMonth) {
        this.openFromMonth = openFromMonth;
    }

    public int getOpenToMonth() {
        return openToMonth;
    }

    public void setOpenToMonth(int openToMonth) {
        this.openToMonth = openToMonth;
    }

    public double getDailyFee() {
        return dailyFee;
    }

    public void setDailyFee(double dailyFee) {
        this.dailyFee = dailyFee;
    }

    @Override
    public String toString() {
        return "Campground{" +
                "campgroundId=" + campgroundId +
                ", parkId=" + parkId +
                ", name='" + name + '\'' +
                ", openFromMonth=" + openFromMonth +
                ", openToMonth=" + openToMonth +
                ", dailyFee=" + dailyFee +
                '}';
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        }
        if ((o == null) || (o.getClass() != this.getClass())){
            return false;
        }
        Campground cg = (Campground) o;
        return campgroundId == cg.campgroundId &&
                parkId == cg.parkId &&
                name.equals(cg.name) &&
                openFromMonth == cg.openFromMonth &&
                openToMonth == cg.openToMonth &&
                dailyFee == cg.dailyFee;
        
    }

    @Override
    public int hashCode(){

        int hash = 6969;
        hash = 69 * hash + parkId + campgroundId;
        hash += 100 * dailyFee + openFromMonth * openToMonth + name.hashCode();
        return hash;
    }
}
