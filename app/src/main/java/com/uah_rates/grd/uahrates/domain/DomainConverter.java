package com.uah_rates.grd.uahrates.domain;

import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.data.entity.EntityRate;
import com.uah_rates.grd.uahrates.domain.model.pojo.Bond;
import com.uah_rates.grd.uahrates.domain.model.pojo.Rate;

import java.util.List;


public class DomainConverter {

    public static Bond bondRESTModelConverter(EntityBond entityBond) {

        String auctiondate = entityBond.getAuctiondate();
        Integer auctionnum = entityBond.getAuctionnum();
        String valcode = entityBond.getValcode();
        String stockcode = entityBond.getStockcode();
        String paydate = entityBond.getPaydate();
        String repaydate = entityBond.getRepaydate();
        Integer stockrestrict = entityBond.getStockrestrict();
        Integer stockrestrictn = entityBond.getStockrestrictn();
        Float incomelevel = entityBond.getIncomelevel();
        Float avglevel = entityBond.getAvglevel();
        Integer amount = entityBond.getAmount();
        Integer amountn = entityBond.getAmountn();
        Float attraction = entityBond.getAttraction();


        return new Bond(auctiondate,
                auctionnum,
                valcode,
                stockcode,
                paydate,
                repaydate,
                stockrestrict,
                stockrestrictn,
                incomelevel,
                avglevel,
                amount,
                amountn,
                attraction);

    }

    public static Rate rateRESTModelConverter(EntityRate current) {

        Integer r030 = current.r030;
        String txt = current.txt;
        Float rate = current.rate;
        String cc = current.cc;
        String exchangedate = current.exchangedate;

        return new Rate(r030, txt, rate, cc, exchangedate);
    }
}
