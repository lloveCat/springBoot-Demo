package com.lhh.demo.pojo;

import java.io.Serializable;

public class UserInfo implements Serializable {

    private static final long serialVersionUID = -8366929034564774346L;
    private String openId;
    private String nikeName;
    private String gender;
    private String city;
    private String province;
    private String country;
    private String chooseCities;

    @Override
    public String toString() {
        return "UserInfo{" +
                "openId='" + openId + '\'' +
                ", nikeName='" + nikeName + '\'' +
                ", gender='" + gender + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", chooseCities='" + chooseCities + '\'' +
                ", language='" + language + '\'' +
                '}';
    }

    public UserInfo(String nikeName, String city, String province, String country) {
        this.nikeName = nikeName;
        this.city = city;
        this.province = province;
        this.country = country;
    }

    public String getChooseCities() {
        return chooseCities == null ? "" : chooseCities;
    }

    public void setChooseCities(String chooseCities) {
        this.chooseCities = chooseCities;
    }

    private String language;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNikeName() {
        return nikeName;
    }

    public void setNikeName(String nikeName) {
        this.nikeName = nikeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}