package com.eguzeler.rest.converters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 03.06.2017.
 */

public class UserDetailedList {
    @SerializedName("userdetailedlists")
    @Expose
    private List<UserDetail> userdetailedlists = null;

    public UserDetailedList() {
    }

    /**
     *
     * @param userdetailedlists
     */
    public UserDetailedList(List<UserDetail> userdetailedlists) {
        super();
        this.userdetailedlists = userdetailedlists;
    }

    public List<UserDetail> getUserdetailedlists() {
        return userdetailedlists;
    }

    public void setUserdetailedlists(List<UserDetail> userdetailedlists) {
        this.userdetailedlists = userdetailedlists;
    }

}