package com.analysis.control;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

import com.crawl.control.Crawler;
import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CrawlResultPackage;

/**
 * Class for doing the analyzing things.
 * @author mschimpf
 *
 */
public class AnaCrack {
    static Logger logger = Logger.getLogger(AnaCrack.class);

    private Collection<CrawlResultPackage> crawlCollection;
    private int average;
    private int min;
    private int max;
    private int median;
    private int mode;
    private int standardDeviation;
    
	/**
	 * 
	 * @param inputInputHowMuch Give me the 10 best offers
	 * @param inputCraigslistAlgorithmEnum To use algorithm
	 * @param inputLowerControlLimit Lower control limit
	 * @param inputHigherControlLimit higher control limit
	 * @return
	 */
	public Collection<CrawlResultPackage> getBestOffers(Collection<CrawlResultPackage> inputCrawlerResultColl, int inputInputHowMuch, CraigslistAlgorithmEnum inputCraigslistAlgorithmEnum, int inputLowerControlLimit, int inputHigherControlLimit){
	    this.setCrawlCollection(inputCrawlerResultColl);
	    
        for (CrawlResultPackage myPackage : inputCrawlerResultColl) {
            logger.info("CrawlResultPackage\n" + myPackage.toString());
        }
	    
		return new ArrayList<CrawlResultPackage>();
		
	}
	
	public Collection<CrawlResultPackage> getCrawlCollection() {
        return crawlCollection;
    }

    private void setCrawlCollection(Collection<CrawlResultPackage> crawlCollection) {
        this.crawlCollection = crawlCollection;
    }

    /**
	 * 
	 */
	private int getAverage(){
		return this.average;
	}

	private void setAverage(int average) {
        this.average = average;
    }

    private void setMin(int min) {
        this.min = min;
    }

    private void setMax(int max) {
        this.max = max;
    }

    private void setMedian(int median) {
        this.median = median;
    }

    private void setMode(int mode) {
        this.mode = mode;
    }

    private void setStandardDeviation(int standardDeviation) {
        this.standardDeviation = standardDeviation;
    }

    /**
	 * 
	 */
	public int getMin(){
		return this.min;
	}
	
	/**
	 * 
	 */	
	public int getMax(){
		return this.mode;
	}
	
	/**
	 * 
	 */	
	public int getMedian(){
		return this.median;
	}

	/**
	 * 
	 */
	public int getMode(){
		return this.mode;
	}
	
	/**
	 * 
	 */	
	public double getStandardDeviation(){
		return this.standardDeviation;
	}
}
