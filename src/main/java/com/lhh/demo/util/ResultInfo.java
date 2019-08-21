package com.lhh.demo.util;

public class ResultInfo {

    public static final int RESULT_SUCCESS = 0; //成功
    public static final int RESULT_ERROR = -1;  //错误
    public static final int RESULT_TIMEOUT = 1; //session超时

    private int status = RESULT_SUCCESS;    //响应状态

    private String statusString = "SUCCESS"; //响应状态描述信息

    private Object data;    //返回数据

    public ResultInfo(){}
    public ResultInfo(int status, String statusString) {
        this.statusString = statusString;
        this.status = status;
    }
    public ResultInfo(int status, String statusString, Object data) {
        super();
        this.status = status;
        this.statusString = statusString;
        this.data = data;
    }

    public static ResultInfo error() {
        return new ResultInfo(RESULT_ERROR,"ERROR");
    }
    public static ResultInfo error(String errorMessage) {
        return new ResultInfo(RESULT_ERROR, errorMessage);
    }
    public static  ResultInfo error(String errorMessage, Object object) {
        return new ResultInfo(RESULT_ERROR, errorMessage, object);
    }

    public static  ResultInfo sessionOut() {
        return new ResultInfo(RESULT_TIMEOUT,"登录超时");
    }

    public static  ResultInfo result(int status, String statusString) {
        return new ResultInfo(status, statusString);
    }
    public static  ResultInfo result(int status, String statusString, Object object) {
        return new ResultInfo(status, statusString, object);
    }

    public static  ResultInfo success() {
        return new ResultInfo(RESULT_SUCCESS, "SUCCESS");
    }
    public static  ResultInfo success(String statusString){
        return new ResultInfo(RESULT_SUCCESS, statusString);
    }
    public static  ResultInfo success(String statusString, Object object) {
        return new ResultInfo(RESULT_SUCCESS, statusString, object);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusString() {
        return statusString;
    }

    public void setStatusString(String statusString) {
        this.statusString = statusString;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
