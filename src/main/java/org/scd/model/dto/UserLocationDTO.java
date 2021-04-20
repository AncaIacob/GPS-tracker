package org.scd.model.dto;

import com.sun.istack.NotNull;

import java.util.Date;

public class UserLocationDTO {
    private String latitude;
    private String longitude;
    private Date date;


    public UserLocationDTO(String latitude, String longitude, String email) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.date = new Date();

    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}