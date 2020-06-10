package com.lhh.demo.util.emsBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by lai.huihui on 2020/5/29.
 * EMS物流添加订单请求Bean
 */
public class EmsCreateOrderRequest {
    @SerializedName("created_time")
    private String createdTime;     //订单接入时间（系统时间）
    @SerializedName("sender_no")
    private String senderNo;        //协议客户代码（邮政13-15位大客户代码）
    @SerializedName("mailType")
    private String mailType;        //电商标志
    @SerializedName("wh_code")
    private String whCode;          //用户揽件机构编号（当地邮局获取）
    @SerializedName("logistics_order_no")
    private String logisticsOrderNo;    //物流订单号
    @SerializedName("batch_no")
    private String batchNo;             //批次号
    @SerializedName("biz_product_no")
    private String bizProductNo;        //业务产品代码
    //重量(g)
    @SerializedName("weight")
    private Integer weight;             //重量
    //体积(cm3)
    @SerializedName("volume")
    private Float volume;               //体积
    //长度(cm)
    @SerializedName("length")
    private Float length;               //长度
    //宽度(cm)
    @SerializedName("width")
    private Float width;                //宽度
    //高度(cm)
    @SerializedName("height")
    private Float height;               //高度

    @SerializedName("postage_total")
    private Float postageTotal;         //邮费
    @SerializedName("postage_currency")
    private String postageCurrency;     //邮资币制

    @SerializedName("contents_total_weight")
    private Integer contentsTotalWeight;    //内件总重量(g)
    @SerializedName("contents_total_value")
    private Float contentsTotalValue;       //内件总价值(元)

    @SerializedName("transfer_type")
    private String transferType;            //邮寄交通方式（HK：航空，SLL：水陆路）
    @SerializedName("battery_flag")
    private String batteryFlag;             //是否有电池（0：无，1：有）

    @SerializedName("pickup_notes")
    private String pickupNotes;             //备注

    @SerializedName("insurance_flag")
    private String insuranceFlag;           //保险保价标志（0：基本，1：保价， 2：保险）
    @SerializedName("insurance_amount")
    private Float insuranceAmount;          //保价金额（元）

    @SerializedName("undelivery_option")
    private Integer undeliveryOption;       //邮件不能被投递策略（1：放弃，2：退回）

    @SerializedName("valuable_flag")
    private String valuableFlag;            //贵品标识（0：无，1：有）
    @SerializedName("declare_source")
    private String declareSource;           //申报来源（2：企业申报，3：个人税款复核）
    @SerializedName("declare_type")
    private String declareType;             //申报类型（1：物品，2：货物）
    @SerializedName("declare_curr_code")
    private String declareCurrCode;         //申报币制代码(固定USD)

    @SerializedName("printcode")
    private String printCode;               //打印格式
    @SerializedName("barcode")
    private String barCode;                 //自定义编号

    @SerializedName("forecastshut")
    private String forecastShut;            //预报关（不知道填0）
    @SerializedName("mail_sign")
    private String mailSign;                //9610标志（1：是，2：否）

    @SerializedName("sender")
    private EmsAddress sender;              //发件人信息
    @SerializedName("pickup")
    private EmsAddress pickup;              //发货人信息
    @SerializedName("receiver")
    private EmsAddress receiver;             //收件人信息
    @SerializedName("items")
    private List<EmsItem> items;            //商品信息

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getSenderNo() {
        return senderNo;
    }

    public void setSenderNo(String senderNo) {
        this.senderNo = senderNo;
    }

    public String getMailType() {
        return mailType;
    }

    public void setMailType(String mailType) {
        this.mailType = mailType;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getLogisticsOrderNo() {
        return logisticsOrderNo;
    }

    public void setLogisticsOrderNo(String logisticsOrderNo) {
        this.logisticsOrderNo = logisticsOrderNo;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBizProductNo() {
        return bizProductNo;
    }

    public void setBizProductNo(String bizProductNo) {
        this.bizProductNo = bizProductNo;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public Float getLength() {
        return length;
    }

    public void setLength(Float length) {
        this.length = length;
    }

    public Float getWidth() {
        return width;
    }

    public void setWidth(Float width) {
        this.width = width;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public Float getPostageTotal() {
        return postageTotal;
    }

    public void setPostageTotal(Float postageTotal) {
        this.postageTotal = postageTotal;
    }

    public String getPostageCurrency() {
        return postageCurrency;
    }

    public void setPostageCurrency(String postageCurrency) {
        this.postageCurrency = postageCurrency;
    }

    public Integer getContentsTotalWeight() {
        return contentsTotalWeight;
    }

    public void setContentsTotalWeight(Integer contentsTotalWeight) {
        this.contentsTotalWeight = contentsTotalWeight;
    }

    public Float getContentsTotalValue() {
        return contentsTotalValue;
    }

    public void setContentsTotalValue(Float contentsTotalValue) {
        this.contentsTotalValue = contentsTotalValue;
    }

    public String getTransferType() {
        return transferType;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public String getBatteryFlag() {
        return batteryFlag;
    }

    public void setBatteryFlag(String batteryFlag) {
        this.batteryFlag = batteryFlag;
    }

    public String getPickupNotes() {
        return pickupNotes;
    }

    public void setPickupNotes(String pickupNotes) {
        this.pickupNotes = pickupNotes;
    }

    public String getInsuranceFlag() {
        return insuranceFlag;
    }

    public void setInsuranceFlag(String insuranceFlag) {
        this.insuranceFlag = insuranceFlag;
    }

    public Float getInsuranceAmount() {
        return insuranceAmount;
    }

    public void setInsuranceAmount(Float insuranceAmount) {
        this.insuranceAmount = insuranceAmount;
    }

    public Integer getUndeliveryOption() {
        return undeliveryOption;
    }

    public void setUndeliveryOption(Integer undeliveryOption) {
        this.undeliveryOption = undeliveryOption;
    }

    public String getValuableFlag() {
        return valuableFlag;
    }

    public void setValuableFlag(String valuableFlag) {
        this.valuableFlag = valuableFlag;
    }

    public String getDeclareSource() {
        return declareSource;
    }

    public void setDeclareSource(String declareSource) {
        this.declareSource = declareSource;
    }

    public String getDeclareType() {
        return declareType;
    }

    public void setDeclareType(String declareType) {
        this.declareType = declareType;
    }

    public String getDeclareCurrCode() {
        return declareCurrCode;
    }

    public void setDeclareCurrCode(String declareCurrCode) {
        this.declareCurrCode = declareCurrCode;
    }

    public String getPrintCode() {
        return printCode;
    }

    public void setPrintCode(String printCode) {
        this.printCode = printCode;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getForecastShut() {
        return forecastShut;
    }

    public void setForecastShut(String forecastShut) {
        this.forecastShut = forecastShut;
    }

    public String getMailSign() {
        return mailSign;
    }

    public void setMailSign(String mailSign) {
        this.mailSign = mailSign;
    }

    public EmsAddress getSender() {
        return sender;
    }

    public void setSender(EmsAddress sender) {
        this.sender = sender;
    }

    public EmsAddress getPickup() {
        return pickup;
    }

    public void setPickup(EmsAddress pickup) {
        this.pickup = pickup;
    }

    public EmsAddress getReceiver() {
        return receiver;
    }

    public void setReceiver(EmsAddress receiver) {
        this.receiver = receiver;
    }

    public List<EmsItem> getItems() {
        return items;
    }

    public void setItems(List<EmsItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "EmsCreateOrderRequest{" +
                "createdTime='" + createdTime + '\'' +
                ", senderNo='" + senderNo + '\'' +
                ", mailType='" + mailType + '\'' +
                ", whCode='" + whCode + '\'' +
                ", logisticsOrderNo='" + logisticsOrderNo + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", bizProductNo='" + bizProductNo + '\'' +
                ", weight=" + weight +
                ", volume=" + volume +
                ", length=" + length +
                ", width=" + width +
                ", height=" + height +
                ", postageTotal=" + postageTotal +
                ", postageCurrency='" + postageCurrency + '\'' +
                ", contentsTotalWeight=" + contentsTotalWeight +
                ", contentsTotalValue=" + contentsTotalValue +
                ", transferType='" + transferType + '\'' +
                ", batteryFlag='" + batteryFlag + '\'' +
                ", pickupNotes='" + pickupNotes + '\'' +
                ", insuranceFlag='" + insuranceFlag + '\'' +
                ", insuranceAmount=" + insuranceAmount +
                ", undeliveryOption=" + undeliveryOption +
                ", valuableFlag='" + valuableFlag + '\'' +
                ", declareSource='" + declareSource + '\'' +
                ", declareType='" + declareType + '\'' +
                ", declareCurrCode='" + declareCurrCode + '\'' +
                ", printCode='" + printCode + '\'' +
                ", barCode='" + barCode + '\'' +
                ", forecastShut='" + forecastShut + '\'' +
                ", mailSign='" + mailSign + '\'' +
                ", sender=" + sender +
                ", pickup=" + pickup +
                ", receive=" + receiver +
                ", items=" + items +
                '}';
    }
}
