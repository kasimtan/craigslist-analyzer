package com.analysis.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.apache.log4j.Logger;

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
    
    private int offers;
    private int average;
    private int min;
    private int max;
    private double median;
    private int mode;
    private double standardDeviation;
    
    private int howMuchOffersToReturn;
    private int lowerControlLimit;
    private int higherControlLimit;
    
    
	/**
	 * 
	 * @param inputInputHowMuch Give me the 10 best offers
	 * @param inputCraigslistAlgorithmEnum To use algorithm
	 * @param inputLowerControlLimit Lower control limit
	 * @param inputHigherControlLimit higher control limit
	 * @return
	 */
	public Collection<CrawlResultPackage> getBestOffers(
	        Collection<CrawlResultPackage> inputCrawlerResultColl, 
	        int inputInputHowMuch, 
	        CraigslistAlgorithmEnum inputCraigslistAlgorithmEnum, 
	        int inputLowerControlLimit, 
	        int inputHigherControlLimit){
	   if (inputCrawlerResultColl==null ||
	           inputInputHowMuch<=0 ||
	           inputCraigslistAlgorithmEnum==null ||
	           inputLowerControlLimit<=0 ||
	           inputHigherControlLimit<=0){
	       throw new IllegalArgumentException(""+inputCrawlerResultColl+" "+inputInputHowMuch+" "+inputCraigslistAlgorithmEnum+" "+inputLowerControlLimit+ " "+inputHigherControlLimit);
	   }
	    
	    this.setCrawlCollection(inputCrawlerResultColl);
	    this.setOffers(inputCrawlerResultColl.size());
	    
	    this.howMuchOffersToReturn=inputInputHowMuch;
	    this.lowerControlLimit=inputLowerControlLimit;
	    this.higherControlLimit=inputHigherControlLimit;
	    
	    this.createAverage();
	    this.searchForMinPrice();
	    this.searchForMaxPrice();
	    this.searchForMedian();
	    this.searchForMode();
	    this.searchForStandardDeviation();
	    
	    if (inputCrawlerResultColl.size()<=inputInputHowMuch){
	        return this.getCrawlCollection();
	    }
	    
	    // Which Algoritm?
	    switch (inputCraigslistAlgorithmEnum){
	        case BEST : 
	            return this.getBestOffers();
	        case WORST : 
                return this.getWorstOffers();
	        case HIGHEST : 
                return this.getWorstOffers();
	        case LOWEST : 
                return this.getBestOffers();
	        case DUMBEST : 
                return this.getWorstOffers();
	        default:
                logger.error("The algorithm is unknown!");
                break;                
	    }
	    
		return new ArrayList<CrawlResultPackage>();
	}
	
    /**
     * Get the worst offers
     * @return
     */
    private Collection<CrawlResultPackage> getWorstOffers(){
        Collection<CrawlResultPackage> aCrawlCollTemp=new ArrayList<CrawlResultPackage>();
        
        for (CrawlResultPackage aPack: this.getCrawlCollection()){
            logger.debug("price="+aPack.getPriceOfItem());
            if (   
                    aPack.getPriceOfItem()<this.higherControlLimit &&
                    aPack.getPriceOfItem()>this.lowerControlLimit){
                aCrawlCollTemp.add(aPack);
            }
        }
        
        logger.debug("aCrawlCollTemp="+aCrawlCollTemp.size());
        
        int aInt10Percent=aCrawlCollTemp.size()/10;
        
        Object[] aPackArray=aCrawlCollTemp.toArray();
        
        logger.debug("aPackArray="+aPackArray.length);
        
        Collection<CrawlResultPackage> aCrawlCollTemp2=new ArrayList<CrawlResultPackage>();
        
        for (int i=0+aInt10Percent;i<=aCrawlCollTemp.size()-aInt10Percent-1;i++){
            CrawlResultPackage aPack=(CrawlResultPackage)aPackArray[i];
            logger.debug("aPack="+aPack.getPriceOfItem());
            aCrawlCollTemp2.add(aPack);
        }
        
        logger.debug("aCrawlCollTemp2="+aCrawlCollTemp2.size());
        
        Object[] aPackArrayRet=aCrawlCollTemp2.toArray();
        Collection<CrawlResultPackage> aCrawlCollRet=new ArrayList<CrawlResultPackage>();
                
        // Get the 10 expensivest offers
        for (int i=0;i<=this.howMuchOffersToReturn;i++){
            CrawlResultPackage aPack2=(CrawlResultPackage)aPackArrayRet[i];
            logger.debug(i+". aPack2="+aPack2.getPriceOfItem());
            aCrawlCollRet.add(aPack2);
        }
        
        logger.debug("aCrawlCollRet="+aCrawlCollRet.size());
        return aCrawlCollRet;
    }
	
	
	/**
	 * Get the best offers
	 * @return
	 */
	private Collection<CrawlResultPackage> getBestOffers(){
	    Collection<CrawlResultPackage> aCrawlCollTemp=new ArrayList<CrawlResultPackage>();
	    
	    for (CrawlResultPackage aPack: this.getCrawlCollection()){
	        logger.debug("price="+aPack.getPriceOfItem());
	        if (   
	                aPack.getPriceOfItem()<this.higherControlLimit &&
	                aPack.getPriceOfItem()>this.lowerControlLimit){
	            aCrawlCollTemp.add(aPack);
	        }
	    }
	    
	    logger.debug("aCrawlCollTemp="+aCrawlCollTemp.size());
	    
	    int aInt10Percent=aCrawlCollTemp.size()/10;
	    
	    Object[] aPackArray=aCrawlCollTemp.toArray();
	    
	    logger.debug("aPackArray="+aPackArray.length);
	    
	    Collection<CrawlResultPackage> aCrawlCollTemp2=new ArrayList<CrawlResultPackage>();
	    
	    for (int i=0+aInt10Percent;i<=aCrawlCollTemp.size()-aInt10Percent-1;i++){
	        CrawlResultPackage aPack=(CrawlResultPackage)aPackArray[i];
	        logger.debug("aPack="+aPack.getPriceOfItem());
	        aCrawlCollTemp2.add(aPack);
	    }
	    
	    logger.debug("aCrawlCollTemp2="+aCrawlCollTemp2.size());
	    
	    Object[] aPackArrayRet=aCrawlCollTemp2.toArray();
	    Collection<CrawlResultPackage> aCrawlCollRet=new ArrayList<CrawlResultPackage>();
	    
	    int i=aPackArrayRet.length-1;
	    int littleEqual=aPackArrayRet.length-this.howMuchOffersToReturn;
	    
	    logger.debug("aPackArrayRet.length="+aPackArrayRet.length);
	    logger.debug(i+">="+littleEqual);
	    
	    for (;i>=littleEqual;i--){
	        CrawlResultPackage aPack2=(CrawlResultPackage)aPackArrayRet[i];
	        logger.debug(i+". aPack2="+aPack2.getPriceOfItem());
	        aCrawlCollRet.add(aPack2);
	    }
	    
	    logger.debug("aCrawlCollRet="+aCrawlCollRet.size());
	    return aCrawlCollRet;
	}
	
	/**
	 * Standrd Deviation
	 */
	private void searchForStandardDeviation(){   
        double[] doubleArray=new double[this.getOffers()];
        int a=0;
        
        for (CrawlResultPackage myPackage : this.getCrawlCollection()) {
            logger.debug("CrawlResultPackage\n" + myPackage.toString());
            doubleArray[a]=myPackage.getPriceOfItem();
            a++;
        }
	      
        double sum = 0;

        // Taking the average to numbers
        for(int i =0; i< doubleArray.length; i++) {
         sum = sum + doubleArray[i];
        }

        double mean = sum/doubleArray.length;

        logger.debug(mean);

        double[] deviations = new double[this.howMuchOffersToReturn];

        // Taking the deviation of mean from each numbers
        for(int i = 0; i < deviations.length; i++) {
         deviations[i] = doubleArray[i] - mean ; 
         logger.debug(""+deviations[i]);   
        }

        double[] squares = new double[10];

        // getting the squares of deviations
        for(int i =0; i< squares.length; i++) {
         squares[i] = deviations[i] * deviations[i];
         logger.debug(""+squares[i]);
        }

        sum = 0;

        // adding all the squares
        for(int i =0; i< squares.length; i++) {
         sum = sum + squares[i];
        }

        logger.debug(sum);

        // dividing the numbers by one less than total numbers
        double result = sum / (doubleArray.length - 1);

        double standardDeviation = Math.sqrt(result);
                
        this.setStandardDeviation(standardDeviation);
	    logger.info("Standard Deviation="+this.getStandardDeviation()+" offers="+this.getOffers());
	}  

	
	private void searchForMode() {
        int[] intArray=new int[this.getOffers()];
        int a=0;
        
        for (CrawlResultPackage myPackage : this.getCrawlCollection()) {
            logger.debug("CrawlResultPackage\n" + myPackage.toString());
            intArray[a]=myPackage.getPriceOfItem();
            a++;
        }
	    
	    
	    int maxValue=0, maxCount=0;

	    for (int i = 0; i < intArray.length; ++i) {
	        int count = 0;
	        for (int j = 0; j < intArray.length; ++j) {
	            if (intArray[j] == intArray[i]) ++count;
	        }
	        if (count > maxCount) {
	            maxCount = count;
	            maxValue = intArray[i];
	        }
	    }

	    this.setMode(maxValue);
	    logger.info("Mode="+this.getMode()+" offers="+this.getOffers());
	}	
	
	private void searchForMedian() {
	    int[] intArray=new int[this.getOffers()];
	    int i=0;
	    
        for (CrawlResultPackage myPackage : this.getCrawlCollection()) {
            logger.debug("CrawlResultPackage\n" + myPackage.toString());
            intArray[i]=myPackage.getPriceOfItem();
            i++;
        }
	    	    
	    int middle = intArray.length/2;  // subscript of middle element
	    
	    if (intArray.length%2 == 1) {
	        // Odd number of elements -- return the middle one.
	        double retValue=intArray[middle];
	        this.setMedian(retValue);
	        logger.info("Median="+this.getMedian()+" offers="+this.getOffers());
	        return;
	    } else {
	       // Even number -- return average of middle two
	       // Must cast the numbers to double before dividing.
	       double retValue=(intArray[middle-1] + intArray[middle]) / 2.0;
	       this.setMedian(retValue);
	       logger.info("Median="+this.getMedian()+" offers="+this.getOffers());
	       return;
	    }
	}

	private void searchForMinPrice(){
        int aMinPrice=1000000;
        
        for (CrawlResultPackage myPackage : this.getCrawlCollection()) {
            logger.debug("CrawlResultPackage\n" + myPackage.toString());
         
            if (myPackage.getPriceOfItem()<aMinPrice){
                aMinPrice=myPackage.getPriceOfItem();
                logger.debug("aMinPrice="+aMinPrice);
            }            
        }
        
        this.setMin(aMinPrice);
        
        logger.info("aMinPrice="+this.getMin()+" offers="+this.getOffers());        
	    
	}
	
    private void searchForMaxPrice(){
        int aMaxPrice=0;
        
        for (CrawlResultPackage myPackage : this.getCrawlCollection()) {
            logger.debug("CrawlResultPackage\n" + myPackage.toString());
         
            if (myPackage.getPriceOfItem()>aMaxPrice){
                aMaxPrice=myPackage.getPriceOfItem();
                logger.debug("aMaxPrice="+aMaxPrice);
            }            
        }
        
        this.setMax(aMaxPrice);
        
        logger.info("FINAL aMaxPrice="+this.getMax()+" offers="+this.getOffers());                
    }
	
	/**
	 * 
	 */
	private void createAverage(){
        int aPrice=0;
        
        for (CrawlResultPackage myPackage : this.getCrawlCollection()) {
            logger.debug("CrawlResultPackage\n" + myPackage.toString());
         
            aPrice=aPrice+myPackage.getPriceOfItem();
        }
        
        logger.info("Offers="+this.getOffers()+" price="+aPrice);
        
        if (this.getOffers()==0){
            this.setAverage(-1);
        } else{
            this.setAverage((aPrice/this.getOffers()));
            
            logger.info("average price="+this.getAverage()+" offers="+this.getOffers());    
        }
	}
		
    public int getOffers() {
        return offers;
    }

    private void setOffers(int offers) {
        this.offers = offers;
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

    private void setMedian(double median) {
        this.median = median;
    }

    private void setMode(int mode) {
        this.mode = mode;
    }

    private void setStandardDeviation(double standardDeviation) {
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
		return this.max;
	}
	
	/**
	 * 
	 */	
	public double getMedian(){
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
	
	@Override public String toString(){
	    StringBuilder aRetString=new StringBuilder();
	    
	    aRetString.append("Max="+this.getMax()+"\n");
	    aRetString.append("Median="+this.getMedian()+"\n");
	    aRetString.append("Min="+this.getMin()+"\n");
	    aRetString.append("Mode="+this.getMode()+"\n");
	    aRetString.append("Offers="+this.getOffers()+"\n");
	    aRetString.append("StandardDeviation="+this.getStandardDeviation()+"\n");
	    
	    return aRetString.toString();
	}
}
