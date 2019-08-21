package com.lhh.demo.pojo;

public class LoginRecord {

    private int id;
    private String userId;
    private String loginTime;

    public LoginRecord(String userId, String loginTime) {
        this.userId = userId;
        this.loginTime = loginTime;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
