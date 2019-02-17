package com.uah_rates.grd.uahrates.domain.interactor.model.pojo;


import com.google.gson.annotations.SerializedName;

//POJO should not have to extend class or implement interface
public final class Bond {
    //-----------------------------------------------
//    @SerializedName("auctiondate")
//    public String auctiondate;
//
//    @SerializedName("auctionnum")
//    public Integer auctionnum;
//
//    @SerializedName("valcode")
//    public String valcode;
//
//    @SerializedName("stockcode")
//    public String stockcode;
//
//    @SerializedName("paydate")
//    public String paydate;
//
//    @SerializedName("repaydate")
//    public String repaydate;
//
//    @SerializedName("stockrestrict")
//    public Integer stockrestrict;
//
//    @SerializedName("stockrestrictn")
//    public Integer stockrestrictn;
//
//    @SerializedName("incomelevel")
//    public Float incomelevel;
//
//    @SerializedName("avglevel")
//    public Float avglevel;
//
//    @SerializedName("amount")
//    public Integer amount;
//
//    @SerializedName("amountn")
//    public Integer amountn;
//
//    @SerializedName("attraction")
//    public Float attraction;


   //---------------------------------------------------

    public String auctiondate;
    public Integer auctionnum;
    public String valcode;
    public String stockcode;
    public String paydate;
    public String repaydate;
    public Integer stockrestrict;
    public Integer stockrestrictn;
    public Float incomelevel;
    public Float avglevel;
    public Integer amount;
    public Integer amountn;
    public Float attraction;
   //---------------------------------------------------
   public Bond(String auctiondate, Integer auctionnum, String valcode, String stockcode, String paydate, String repaydate,
               Integer stockrestrict, Integer stockrestrictn, Float incomelevel, Float avglevel,
               Integer amount, Integer amountn, Float attraction) {

       this.auctiondate = auctiondate;
       this.auctionnum = auctionnum;
       this.valcode = valcode;
       this.stockcode = stockcode;
       this.paydate = paydate;
       this.repaydate = repaydate;
       this.stockrestrict = stockrestrict;
       this.stockrestrictn = stockrestrictn;
       this.incomelevel = incomelevel;
       this.avglevel = avglevel;
       this.amount = amount;
       this.amountn = amountn;
       this.attraction = attraction;
   }
   //---------------------------------------------------
    public String getAuctiondate() {
        return auctiondate;
    }

    public void setAuctiondate(String auctiondate) {
        this.auctiondate = auctiondate;
    }

    public Integer getAuctionnum() {
        return auctionnum;
    }

    public void setAuctionnum(Integer auctionnum) {
        this.auctionnum = auctionnum;
    }

    public String getValcode() {
        return valcode;
    }

    public void setValcode(String valcode) {
        this.valcode = valcode;
    }

    public String getStockcode() {
        return stockcode;
    }

    public void setStockcode(String stockcode) {
        this.stockcode = stockcode;
    }

    public String getPaydate() {
        return paydate;
    }

    public void setPaydate(String paydate) {
        this.paydate = paydate;
    }

    public String getRepaydate() {
        return repaydate;
    }

    public void setRepaydate(String repaydate) {
        this.repaydate = repaydate;
    }

    public Integer getStockrestrict() {
        return stockrestrict;
    }

    public void setStockrestrict(Integer stockrestrict) {
        this.stockrestrict = stockrestrict;
    }

    public Integer getStockrestrictn() {
        return stockrestrictn;
    }

    public void setStockrestrictn(Integer stockrestrictn) {
        this.stockrestrictn = stockrestrictn;
    }

    public Float getIncomelevel() {
        return incomelevel;
    }

    public void setIncomelevel(Float incomelevel) {
        this.incomelevel = incomelevel;
    }

    public Float getAvglevel() {
        return avglevel;
    }

    public void setAvglevel(Float avglevel) {
        this.avglevel = avglevel;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmountn() {
        return amountn;
    }

    public void setAmountn(Integer amountn) {
        this.amountn = amountn;
    }

    public Float getAttraction() {
        return attraction;
    }

    public void setAttraction(Float attraction) {
        this.attraction = attraction;
    }
    //-----------------------------------------------

    }


    /*
[
   {
      "auctiondate":"03.07.2014",
      "auctionnum":113,
      "valcode":"UAH",
      "stockcode":"UA4000180210",
      "paydate":"04.07.2014",
      "repaydate":"01.10.2014",
      "stockrestrict":40000,
      "stockrestrictn":0,
      "incomelevel":12.0,
      "avglevel":12.0,
      "amount":40000,
      "amountn":0,
      "attraction":3.88628E7
   },
   {
      "auctiondate":"12.02.2013",
      "auctionnum":34,
      "valcode":"USD",
      "stockcode":"UA4000153282",
      "paydate":"13.02.2013",
      "repaydate":"03.02.2016",
      "stockrestrict":0,
      "stockrestrictn":0,
      "incomelevel":0.0,
      "avglevel":0.0,
      "amount":0,
      "amountn":0,
      "attraction":0.0
   },
   {
      "auctiondate":"23.04.2013",
      "auctionnum":85,
      "valcode":"UAH",
      "stockcode":"UA4000165773",
      "paydate":"24.04.2013",
      "repaydate":"08.04.2020",
      "stockrestrict":1310000,
      "stockrestrictn":0,
      "incomelevel":14.3000001907349,
      "avglevel":14.3000001907349,
      "amount":1310000,
      "amountn":0,
      "attraction":1.3134846E9
   }
]
 */



