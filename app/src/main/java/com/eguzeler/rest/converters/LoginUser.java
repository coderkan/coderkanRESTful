package com.eguzeler.rest.converters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 03.06.2017.
 */

public class LoginUser {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("userpassword")
    @Expose
    private String userpassword;

    public LoginUser() {
    }

    /**
     *
     * @param id
     * @param userpassword
     * @param username
     */
    public LoginUser(Integer id, String username, String userpassword) {
        super();
        this.id = id;
        this.username = username;
        this.userpassword = userpassword;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", userpassword='" + userpassword + '\'' +
                '}';
    }
}
