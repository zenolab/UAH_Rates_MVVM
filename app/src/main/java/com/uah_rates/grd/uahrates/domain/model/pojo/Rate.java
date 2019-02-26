package com.uah_rates.grd.uahrates.domain.model.pojo;

import com.google.gson.annotations.SerializedName;

//POJO should not have to extend class or implement interface
public final class Rate
{

    //code  of rate
    @SerializedName("r030")
    public Integer r030;

    @SerializedName("txt")
    public String txt;

    @SerializedName("rate")
    public Float rate;

    @SerializedName("cc")
    public String cc;

    @SerializedName("exchangedate")
    public String exchangedate;

    public Rate(Integer r030, String txt, Float rate, String cc, String exchangedate) {
        this.r030 = r030;
        this.txt = txt;
        this.rate = rate;
        this.cc = cc;
        this.exchangedate = exchangedate;
    }


    public Integer getR030() {
        return r030;
    }

    public void setR030(Integer r030) {
        this.r030 = r030;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Float getRate() {

        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }


}