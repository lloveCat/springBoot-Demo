package com.lhh.demo.util.emsBean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lai.huihui on 2020/5/29.
 * EMS物流用户地址Bean
 */
public class EmsAddress implements Serializable {
    @SerializedName("name")
    private String name;        //姓名
    @SerializedName("company")
    private String company;     //公司
    @SerializedName("post_code")
    private String postCode;    //邮编
    @SerializedName("phone")
    private String phone;       //电话
    @SerializedName("mobile")
    private String mobile;      //移动电话
    @SerializedName("email")
    private String email;       //邮箱
    @SerializedName("id_type")
    private String idType;      //申报信息来源为个人信息申报时必填
    @SerializedName("id_no")
    private String idNo;        //个人申报时必填
    @SerializedName("nation")
    private String nation;      //国家，俄罗斯联邦必填
    @SerializedName("province")
    private String province;    //省份，俄罗斯联邦必填
    @SerializedName("city")
    private String city;        //城市，俄罗斯联邦必填
    @SerializedName("county")
    private String county;      //县（区），俄罗斯联邦必填
    @SerializedName("address")
    private String address;     //详细地址
    @SerializedName("gis")
    private String gis;         //GIS坐标
    @SerializedName("linker")
    private String linker;      //用户联系人，俄罗斯联邦必填

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGis() {
        return gis;
    }

    public void setGis(String gis) {
        this.gis = gis;
    }

    public String getLinker() {
        return linker;
    }

    public void setLinker(String linker) {
        this.linker = linker;
    }

    @Override
    public String toString() {
        return "EmsAddress{" +
                "name='" + name + '\'' +
                ", company='" + company + '\'' +
                ", postCode='" + postCode + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", nation='" + nation + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", address='" + address + '\'' +
                ", gis='" + gis + '\'' +
                ", linker='" + linker + '\'' +
                '}';
    }
}
