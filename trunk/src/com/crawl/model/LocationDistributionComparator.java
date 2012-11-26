package com.crawl.model;

import java.util.Comparator;

/**
 * Location distribution comparator sort object.
 * @author Team Kappa
 *
 */
public class LocationDistributionComparator implements Comparator<LocationDistribution>{
    /**
     * Sort method.
     */
    @Override public int compare(LocationDistribution inputOne, LocationDistribution inputTwo){
        if (inputOne.getCount()==inputTwo.getCount()){
            return 0;
        } else {
            return inputOne.getCount()-inputTwo.getCount();
        }
    }
}