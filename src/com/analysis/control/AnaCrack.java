package com.analysis.control;

import java.util.ArrayList;
import java.util.Collection;

import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CrawlResultPackage;

/**
 * Class for doing the analyzing things.
 * @author mschimpf
 *
 */
public class AnaCrack {
	/**
	 * 
	 * @param inputInputHowMuch Give me the 10 best offers
	 * @param inputCraigslistAlgorithmEnum To use algorithm
	 * @param inputLowerControlLimit Lower control limit
	 * @param inputHigherControlLimit higher control limit
	 * @return
	 */
	public Collection<CrawlResultPackage> getBestOffers(Collection<CrawlResultPackage> inputCrawlerResultColl, int inputInputHowMuch, CraigslistAlgorithmEnum inputCraigslistAlgorithmEnum, int inputLowerControlLimit, int inputHigherControlLimit){
		return new ArrayList<CrawlResultPackage>();
	}
	
	/**
	 * 
	 */
	public int getAverage(){
		return -1;
	}

	/**
	 * 
	 */
	public int getMin(){
		return -1;
	}
	
	/**
	 * 
	 */	
	public int getMax(){
		return -1;
	}
	
	/**
	 * 
	 */	
	public int getMedian(){
		return -1;
	}

	/**
	 * 
	 */
	public int getMode(){
		return -1;
	}
	
	/**
	 * 
	 */	
	public double getStandardDeviation(){
		return 0.0;
	}
}
