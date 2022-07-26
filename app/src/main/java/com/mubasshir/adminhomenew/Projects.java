package com.mubasshir.adminhomenew;

public class Projects {

    String name,address,city,amountin,amountout;

    public Projects() {
    }

    public Projects(String name, String address, String city, String amountin, String amountout) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.amountin = amountin;
        this.amountout = amountout;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAmountin(String amountin) {
        this.amountin = amountin;
    }

    public void setAmountout(String amountout) {
        this.amountout = amountout;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getAmountin() {
        return amountin;
    }

    public String getAmountout() {
        return amountout;
    }
}
