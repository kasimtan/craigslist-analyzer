package com.crawl.model;

import java.util.Comparator;

/**
 * Sort alg.
 * @author Team Kappa.
 *
 */
public class PriceDistributionComparator implements Comparator<PriceDistribution>{
    /**
     * Sort method.
     */
    @Override public int compare(PriceDistribution inputOne, PriceDistribution inputTwo){
        if (inputOne.getCount()==inputTwo.getCount()){
            return 0;
        } else {
            return inputOne.getCount()-inputTwo.getCount();
        }
    }
}
