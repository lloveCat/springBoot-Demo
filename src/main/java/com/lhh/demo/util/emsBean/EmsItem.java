package com.lhh.demo.util.emsBean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by lai.huihui on 2020/5/29.
 * EMS物流货物信息Bean
 */
public class EmsItem implements Serializable {
    @SerializedName("cargo_no")
    private String cargoNo;
    @SerializedName("cargo_name")
    private String cargoName;
    @SerializedName("cargo_name_en")
    private String cargoNameEn;
    @SerializedName("cargo_type_name")
    private String cargoTypeName;
    @SerializedName("cargo_type_name_en")
    private String cargoTypeNameEn;
    @SerializedName("cargo_origin_name")
    private String cargoOriginName;
    @SerializedName("cargo_link")
    private String cargoLink;
    @SerializedName("cargo_quantity")
    private Integer cargoQuantity;
    @SerializedName("cargo_value")
    private Float cargoValue;
    @SerializedName("cost")
    private Float cost;
    @SerializedName("cargo_currency")
    private String cargoCurrency;
    @SerializedName("cargo_weight")
    private Integer cargoWeight;
    @SerializedName("cargo_description")
    private String cargoDescription;
    @SerializedName("cargo_serial")
    private String cargoSerial;
    @SerializedName("unit")
    private String unit;
    @SerializedName("intemsize")
    private String intemSize;

    public String getCargoNo() {
        return cargoNo;
    }

    public void setCargoNo(String cargoNo) {
        this.cargoNo = cargoNo;
    }

    public String getCargoName() {
        return cargoName;
    }

    public void setCargoName(String cargoName) {
        this.cargoName = cargoName;
    }

    public String getCargoNameEn() {
        return cargoNameEn;
    }

    public void setCargoNameEn(String cargoNameEn) {
        this.cargoNameEn = cargoNameEn;
    }

    public String getCargoTypeName() {
        return cargoTypeName;
    }

    public void setCargoTypeName(String cargoTypeName) {
        this.cargoTypeName = cargoTypeName;
    }

    public String getCargoTypeNameEn() {
        return cargoTypeNameEn;
    }

    public void setCargoTypeNameEn(String cargoTypeNameEn) {
        this.cargoTypeNameEn = cargoTypeNameEn;
    }

    public String getCargoOriginName() {
        return cargoOriginName;
    }

    public void setCargoOriginName(String cargoOriginName) {
        this.cargoOriginName = cargoOriginName;
    }

    public String getCargoLink() {
        return cargoLink;
    }

    public void setCargoLink(String cargoLink) {
        this.cargoLink = cargoLink;
    }

    public Integer getCargoQuantity() {
        return cargoQuantity;
    }

    public void setCargoQuantity(Integer cargoQuantity) {
        this.cargoQuantity = cargoQuantity;
    }

    public Float getCargoValue() {
        return cargoValue;
    }

    public void setCargoValue(Float cargoValue) {
        this.cargoValue = cargoValue;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getCargoCurrency() {
        return cargoCurrency;
    }

    public void setCargoCurrency(String cargoCurrency) {
        this.cargoCurrency = cargoCurrency;
    }

    public Integer getCargoWeight() {
        return cargoWeight;
    }

    public void setCargoWeight(Integer cargoWeight) {
        this.cargoWeight = cargoWeight;
    }

    public String getCargoDescription() {
        return cargoDescription;
    }

    public void setCargoDescription(String cargoDescription) {
        this.cargoDescription = cargoDescription;
    }

    public String getCargoSerial() {
        return cargoSerial;
    }

    public void setCargoSerial(String cargoSerial) {
        this.cargoSerial = cargoSerial;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIntemSize() {
        return intemSize;
    }

    public void setIntemSize(String intemSize) {
        this.intemSize = intemSize;
    }

    @Override
    public String toString() {
        return "EmsItem{" +
                "cargoNo='" + cargoNo + '\'' +
                ", cargoName='" + cargoName + '\'' +
                ", cargoNameEn='" + cargoNameEn + '\'' +
                ", cargoTypeName='" + cargoTypeName + '\'' +
                ", cargoTypeNameEn='" + cargoTypeNameEn + '\'' +
                ", cargoOriginName='" + cargoOriginName + '\'' +
                ", cargoLink='" + cargoLink + '\'' +
                ", cargoQuantity=" + cargoQuantity +
                ", cargoValue=" + cargoValue +
                ", cost=" + cost +
                ", cargoCurrency='" + cargoCurrency + '\'' +
                ", cargoWeight=" + cargoWeight +
                ", cargoDescription='" + cargoDescription + '\'' +
                ", cargoSerial='" + cargoSerial + '\'' +
                ", unit='" + unit + '\'' +
                ", intemSize='" + intemSize + '\'' +
                '}';
    }
}
