package com.uah_rates.grd.uahrates.domain.interactor;

import com.uah_rates.grd.uahrates.data.entity.EntityBond;
import com.uah_rates.grd.uahrates.domain.interactor.model.pojo.Bond;

import java.util.List;


public class DomainConverter {


     public static Bond bondRESTModelConverter(EntityBond entityBond){
/*
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
                */
return null;
    }

    public static List<Bond> bondRESTModelConverter(List<EntityBond> entityBond){

//        String auctiondate = entityBond.getAuctiondate();
//        Integer auctionnum = entityBond.getAuctionnum();
//        String valcode = entityBond.getValcode();
//        String stockcode = entityBond.getStockcode();
//        String paydate = entityBond.getPaydate();
//        String repaydate = entityBond.getRepaydate();
//        Integer stockrestrict = entityBond.getStockrestrict();
//        Integer stockrestrictn = entityBond.getStockrestrictn();
//        Float incomelevel = entityBond.getIncomelevel();
//        Float avglevel = entityBond.getAvglevel();
//        Integer amount = entityBond.getAmount();
//        Integer amountn = entityBond.getAmountn();
//        Float attraction = entityBond.getAttraction();
//
//
//        return new Bond(auctiondate,
//                auctionnum,
//                valcode,
//                stockcode,
//                paydate,
//                repaydate,
//                stockrestrict,
//                stockrestrictn,
//                incomelevel,
//                avglevel,
//                amount,
//                amountn,
//                attraction);

        return null;
    }
}
