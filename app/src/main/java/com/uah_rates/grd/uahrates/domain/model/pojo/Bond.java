package com.uah_rates.grd.uahrates.domain.model.pojo;


//POJO should not have to extend class or implement interface
public final class Bond {


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


    public Bond(String auctiondate,
                Integer auctionnum,
                String valcode,
                String stockcode,
                String paydate,
                String repaydate,
                Integer stockrestrict,
                Integer stockrestrictn,
                Float incomelevel,
                Float avglevel,
                Integer amount,
                Integer amountn,
                Float attraction) {

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


}





