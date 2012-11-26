package com.crawl.model;

import java.util.Comparator;

/**
 * Sort Algorithmen.
 * @author Team kappa
 *
 */
public class CrawlResultPackageComparator implements Comparator<CrawlResultPackage>{
    /**
     * Sort alg.
     */
    @Override public int compare(CrawlResultPackage inputOne, CrawlResultPackage inputTwo){
        if (inputOne.getPriceOfItem()==inputTwo.getPriceOfItem()){
            return 0;
        } else {
            return inputOne.getPriceOfItem()-inputTwo.getPriceOfItem();
        }
    }
}