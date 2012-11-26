package com.crawl.model;

import java.util.Comparator;

/**
 * Sort alg.
 * @author mschimpf
 *
 */
public class PriceDistributionComparator implements Comparator<PriceDistribution>{
    /**
     * 
     */
    @Override public int compare(PriceDistribution inputOne, PriceDistribution inputTwo){
        if (inputOne.getCount()==inputTwo.getCount()){
            return 0;
        } else {
            return inputOne.getCount()-inputTwo.getCount();
        }
    }
}
