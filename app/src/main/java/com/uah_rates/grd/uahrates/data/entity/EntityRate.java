package com.uah_rates.grd.uahrates.data.entity;

import com.google.gson.annotations.SerializedName;

public final class EntityRate {

    @SerializedName("code")
    public Integer r030;

    @SerializedName("txt")
    public String txt;

    @SerializedName("rate")
    public Float rate;

    @SerializedName("acronym")
    public String cc;

    @SerializedName("exchangedate")
    public String exchangedate;

    public EntityRate(Integer r030, String txt, Float rate, String cc, String exchangedate) {
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

/*
[
        {
        "code":36,"txt":"Австралійський долар","rate":18.573267,"acronym":"AUD","exchangedate":"11.03.2019"
        }
        ,{
        "code":124,"txt":"Канадський долар","rate":19.657148,"acronym":"CAD","exchangedate":"11.03.2019"
        }
]
*/