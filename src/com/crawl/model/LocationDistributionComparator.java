package com.crawl.model;

import java.util.Comparator;

/**
 * 
 * @author mschimpf
 *
 */
public class LocationDistributionComparator implements Comparator<LocationDistribution>{
    /**
     * 
     */
    @Override public int compare(LocationDistribution inputOne, LocationDistribution inputTwo){
        if (inputOne.getCount()==inputTwo.getCount()){
            return 0;
        } else {
            return inputOne.getCount()-inputTwo.getCount();
        }
    }
}