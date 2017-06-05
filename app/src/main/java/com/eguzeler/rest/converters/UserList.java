package com.eguzeler.rest.converters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 03.06.2017.
 */

public class UserList {

    @SerializedName("userlists")
    @Expose
    private List<User> userlists = null;

    public UserList() {
    }

    /**
     * @param userlists
     */
    public UserList(List<User> userlists) {
        super();
        this.userlists = userlists;
    }

    public List<User> getUserlists() {
        return userlists;
    }

    public void setUserlists(List<User> userlists) {
        this.userlists = userlists;
    }
}