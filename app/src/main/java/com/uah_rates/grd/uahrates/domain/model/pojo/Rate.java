package com.uah_rates.grd.uahrates.domain.model.pojo;


public final class Rate
{


    public Integer code;
    public String txt;
    public Float rate;
    public String acronym;
    public String exchangedate;

    public Rate(Integer code, String txt, Float rate, String acronym, String exchangedate) {
        this.code = code;
        this.txt = txt;
        this.rate = rate;
        this.acronym = acronym;
        this.exchangedate = exchangedate;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
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

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(String exchangedate) {
        this.exchangedate = exchangedate;
    }


}