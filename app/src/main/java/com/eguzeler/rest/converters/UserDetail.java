package com.eguzeler.rest.converters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 03.06.2017.
 */

public class UserDetail {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("firstname")
    @Expose
    private String firstname;
    @SerializedName("lastname")
    @Expose
    private String lastname;
    @SerializedName("job")
    @Expose
    private String job;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("address")
    @Expose
    private String address;

    @SerializedName("user_avatar")
    @Expose
    private String user_avatar;


    public UserDetail() {
    }

    /**
     *
     * @param id
     * @param phone
     * @param address
     * @param job
     * @param lastname
     * @param firstname
     * @param user_avatar
     */
    public UserDetail(Integer id, String firstname, String lastname, String job, String phone, String address, String user_avatar) {
        super();
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.job = job;
        this.phone = phone;
        this.address = address;
        this.user_avatar = user_avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserAvatar() {
        return user_avatar;
    }

    public void setUserAvatar(String user_avatar) {
        this.user_avatar = user_avatar;
    }

    public String getFullName(){
        return (this.firstname + " " + this.lastname);
    }

    @Override
    public String toString() {
        return "UserDetail{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", job='" + job + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", user_avatar='" + user_avatar + '\'' +
                '}';
    }
}