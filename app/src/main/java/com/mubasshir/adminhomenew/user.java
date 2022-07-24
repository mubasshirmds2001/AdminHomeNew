package com.mubasshir.adminhomenew;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class user {
    private String pname;
    private String addressname;
    private String pcity;

    public user() {
    }

    public user(String pname, String addressname, String pcity) {
        this.pname = pname;
        this.addressname = addressname;
        this.pcity = pcity;
    }

    public String getPname() {
        return pname;
    }

    public String getAddressname() {
        return addressname;
    }

    public String getPcity() {
        return pcity;
    }
}
