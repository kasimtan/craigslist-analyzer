package com.analysis.control;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CrawlResultPackage;
import com.crawl.model.LocationDistribution;
import com.crawl.model.LocationDistributionComparator;
import com.crawl.model.PriceDistribution;

/**
 * Class for doing the analyzing things.
 * @author mschimpf
 *
 */
public class AnaCrack {
    static Logger logger = Logger.getLogger(AnaCrack.class);

    private Collection<CrawlResultPackage> crawlCollection;
    
    private int offers;
    private double average;
    private int min;
    private int max;
    private double median;
    private int mode;
    private double standardDeviation;
    
    private int howManyOffersToReturn;
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
	public Collection<CrawlResultPackage> analyse(
	        Collection<CrawlResultPackage> inputCrawlerResultColl, 
	        int inputHowManyResults, 
	        CraigslistAlgorithmEnum inputCraigslistAlgorithmEnum, 
	        int inputLowerControlLimit, 
	        int inputHigherControlLimit){
	   if (inputCrawlerResultColl==null ||
	           inputHowManyResults<=0 ||
	           inputCraigslistAlgorithmEnum==null ||
	           inputLowerControlLimit<=0 ||
	           inputHigherControlLimit<=0){
	       throw new IllegalArgumentException(""+inputHowManyResults+" "+inputHowManyResults+" "+inputCraigslistAlgorithmEnum+" "+inputLowerControlLimit+ " "+inputHigherControlLimit);
	   }
	   
	   if (inputCrawlerResultColl.size()==0){
	       this.logger.info("No Infos!");
	       return inputCrawlerResultColl;
	   }
	    
	    this.setCrawlCollection(inputCrawlerResultColl);
	    this.setOffers(inputCrawlerResultColl.size());
	    
	    this.howManyOffersToReturn=inputHowManyResults;
	    this.lowerControlLimit=inputLowerControlLimit;
	    this.higherControlLimit=inputHigherControlLimit;
	    
	    this.createAverage();
	    this.searchForMinPrice();
	    this.searchForMaxPrice();
	    this.searchForMedian();
	    this.searchForMode();
	    this.searchForStandardDeviation();
	    
	    // If the findings count is lower or equal than the
	    // items, nothing to do...
	    if (inputCrawlerResultColl.size()<=inputHowManyResults){
	        return this.getCrawlCollection();
	    }
	    
	    // Which Algoritm?
	    switch (inputCraigslistAlgorithmEnum){
	        case BEST : 
	            return this.reverseOrder(this.getBestOffers());
	        case WORST : 
                return this.reverseOrder(this.getWorstOffers());
	        case HIGHEST : 
                return this.reverseOrder(this.getWorstOffers());
	        case LOWEST : 
                return this.reverseOrder(this.getBestOffers());
	        case DUMBEST : 
                return this.reverseOrder(this.getWorstOffers());
	        default:
                logger.error("The algorithm is unknown!");
                break;                
	    }
	    
		return new ArrayList<CrawlResultPackage>();
	}
	
	/**
	 * Reverse the order of the collection.
	 * @param inputColl
	 * @return
	 */
	private Collection<CrawlResultPackage> reverseOrder(Collection<CrawlResultPackage> inputColl){
	    Collection<CrawlResultPackage> aRetColl=new ArrayList<CrawlResultPackage>();
	    
	    Object[] aArrayCralObjects=inputColl.toArray();
	    
	    for (int i=0;i<aArrayCralObjects.length;i++){
	        logger.debug("i="+i);
	        aRetColl.add((CrawlResultPackage)aArrayCralObjects[i]);
	    }
	    
	    return aRetColl;
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
        for (int i=0;i<=this.howManyOffersToReturn;i++){
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
	    
	    logger.info("aPackArrayRet.length="+aPackArrayRet.length);
	    
	    Collection<CrawlResultPackage> aCrawlCollRet=new ArrayList<CrawlResultPackage>();
	    
	    int i=aPackArrayRet.length-1;
	    int littleEqual=aPackArrayRet.length-this.howManyOffersToReturn;
	    
	    logger.info("aPackArrayRet.length="+aPackArrayRet.length);
	    logger.info(i+">="+littleEqual);
	    
	    try {
	        for (;i>=littleEqual;i--){
	            CrawlResultPackage aPack2=(CrawlResultPackage)aPackArrayRet[i];
	            logger.debug(i+". aPack2="+aPack2.getPriceOfItem());
	            aCrawlCollRet.add(aPack2);
	        }
	    } catch (ArrayIndexOutOfBoundsException e){
	        logger.fatal(e.toString());
	        e.printStackTrace();
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

        double mean = sum*1.0/doubleArray.length;

        logger.debug(mean);

        double[] deviations = new double[doubleArray.length];

        // Taking the deviation of mean from each numbers
        for(int i = 0; i < deviations.length; i++) {
         deviations[i] = doubleArray[i] - mean ; 
         logger.debug(""+deviations[i]);   
        }

        double[] squares = new double[doubleArray.length];

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
        double result = sum*1.0 / (doubleArray.length - 1);

        double standardDeviation = Math.sqrt(result);
                
        this.setStandardDeviation(standardDeviation);
	    logger.info("Standard Deviation="+this.getStandardDeviation()+" offers="+this.getOffers());
	}  

	/**
	 * 
	 */
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
	
	/**
	 * 
	 */
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

	/**
	 * 
	 */
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
	
	/**
	 * 
	 */
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
            this.setAverage((aPrice*1.0/this.getOffers()));
            
            logger.info("average price="+this.getAverage()+" offers="+this.getOffers());    
        }
	}
	
	/**
	 * 
	 * @return
	 */
    public int getOffers() {
        return offers;
    }

    /**
     * 
     * @param offers
     */
    private void setOffers(int offers) {
        this.offers = offers;
    }	
	
    /**
     * 
     * @return
     */
	public Collection<CrawlResultPackage> getCrawlCollection() {
        return crawlCollection;
    }

	/**
	 * 
	 * @param crawlCollection
	 */
    private void setCrawlCollection(Collection<CrawlResultPackage> crawlCollection) {
        this.crawlCollection = crawlCollection;
    }

    /**
	 * 
	 */
	public double getAverage(){
		return this.average;
	}

	/**
	 * 
	 * @param average
	 */
	private void setAverage(double average) {
        this.average = average;
    }

	/**
	 * 
	 * @param min
	 */
    private void setMin(int min) {
        this.min = min;
    }

    /**
     * 
     * @param max
     */
    private void setMax(int max) {
        this.max = max;
    }

    /**
     * 
     * @param median
     */
    private void setMedian(double median) {
        this.median = median;
    }

    /**
     * 
     * @param mode
     */
    private void setMode(int mode) {
        this.mode = mode;
    }

    /**
     * 
     * @param standardDeviation
     */
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
	
	/**
	 * 
	 */
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
	
	/**
	 * Create Location DIstribution
	 * @return
	 */
	public Collection<LocationDistribution> getLocationDistribution(){
        if (this.getCrawlCollection()==null){
            logger.error("getCrawlCollection --- Collection is NULL");
            return new ArrayList<LocationDistribution>();
        }
	    
	    logger.debug("Start");
	    logger.debug("size="+this.getCrawlCollection().size());
	    	    
	    SortedMap<String, LocationDistribution> aSortedMap=new TreeMap<String, LocationDistribution>();
	    
	    for (CrawlResultPackage aCrawlResultPackage : this.getCrawlCollection()){
	        Collection<String> aStringLocColl=aCrawlResultPackage.getLocations();
	        
	        logger.debug("aStringLocColl="+aCrawlResultPackage.getLocationsAsString());
	        
	        for (String aStringGet: aStringLocColl){
	            String aCurrString=aStringGet.toLowerCase();
	            
	            LocationDistribution aLocDist=aSortedMap.get(aCurrString);
	            logger.debug("aLocDist="+aLocDist);
	            
	            if (aLocDist==null){
	                LocationDistribution aLoc=new LocationDistribution();
	                aLoc.setAreaName(aCurrString);
	                aLoc.setCount(1);
	                aSortedMap.put(aCurrString, aLoc);
	            } else {
	                aLocDist.setCount(aLocDist.getCount()+1);
	            }
	        }
	    }
	    
	    Collection<LocationDistribution> aRetColl1=aSortedMap.values();
	    	    
	    logger.info("aRetColl1 --- SIZE="+aRetColl1.size());
	    
	    // Sort it...
	    Collection<LocationDistribution> aRetColl2=this.bubbleSort(aRetColl1.toArray());
	    
	    logger.info("aRetColl2 --- SIZE="+aRetColl2.size());
	    
	    // Cut it to ten items
	    Collection<LocationDistribution> aRetColl3=this.cutAndMakeItProper(aRetColl2);
	    
	    logger.info("aRetColl3 --- SIZE="+aRetColl3.size());
	    	    
	    return aRetColl3;
	}
	
	private Collection<LocationDistribution> cutAndMakeItProper(Collection<LocationDistribution> inputColl){
	    // If there are less or equal x items, do nothing and return the input collection
	    final int aCountItems=20;
	    
	    if (inputColl.size()<=aCountItems){
	        logger.info("Collection is less then "+aCountItems+"!");
	        return inputColl;
	    }
	    
	    Collection<LocationDistribution> aFirstStepColl=new ArrayList<LocationDistribution>();
	    
	    Object[] arrObj=inputColl.toArray();
	    
	    // Reverse it
	    for (int i=arrObj.length-1;i>0;i--){
	        aFirstStepColl.add((LocationDistribution)arrObj[i]);
	    }
	    
	    Collection<LocationDistribution> aSecondStepColl=new ArrayList<LocationDistribution>();
	    
	    Object[] objArrFin=aFirstStepColl.toArray();
	    
	    int aIntRestCopunter=0;
	    
        for (int i=0;i<objArrFin.length;i++){
            if (i>aCountItems){
                aIntRestCopunter=aIntRestCopunter+((LocationDistribution)objArrFin[i]).getCount();
            }else{
                aSecondStepColl.add((LocationDistribution)objArrFin[i]);
            }
        }	
        
        LocationDistribution aLocationDistributionRest=new LocationDistribution();
        aLocationDistributionRest.setAreaName("Rest");
        aLocationDistributionRest.setCount(aIntRestCopunter);
        aSecondStepColl.add(aLocationDistributionRest);
	    
	    return aSecondStepColl;
	}
	
    /**
     * BubbleSort
     * @param arr
     */
    private Collection<LocationDistribution> bubbleSort(Object[] inputObjArray) {
        for (int i = 0; i < inputObjArray.length; i++) {
            for (int j = 1; j < inputObjArray.length; j++) {
                if (((LocationDistribution)inputObjArray[j]).getCount() < (((LocationDistribution)inputObjArray[j - 1]).getCount())) {
                    LocationDistribution aLocationDistribution = (LocationDistribution)inputObjArray[j];
                    inputObjArray[j] = inputObjArray[j - 1];
                    inputObjArray[j - 1] = aLocationDistribution;
                }
            }
        }
        
        Collection<LocationDistribution> aFinal=new ArrayList<LocationDistribution>();
        
        for (int i=0;i<inputObjArray.length;i++){
            aFinal.add((LocationDistribution)inputObjArray[i]);
        }

        return aFinal;
    }
	
    /**
     * Create Price DIstribution
     * @return
     */
    public Collection<PriceDistribution> getPriceDistribution(){
        if (this.getCrawlCollection()==null){
            logger.error("getCrawlCollection --- Collection is NULL");
            return new ArrayList<PriceDistribution>();
        }
        
        logger.debug("Start");
        logger.debug("size="+this.getCrawlCollection().size());
        
        HashMap<String, String> map = new HashMap<String, String>();
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        for (CrawlResultPackage aCrawlResultPackage : this.getCrawlCollection()) {
            String aStringPrice=""+aCrawlResultPackage.getPriceOfItem();
            if(map.get(aStringPrice) == null) {
                map.put(aStringPrice, "1");
                list.add(Integer.parseInt(aStringPrice));
                logger.debug("aStringPrice=" + aStringPrice);
            }
            else {
                int count = Integer.parseInt((String)map.get(aStringPrice));
                map.put(aStringPrice, String.valueOf(count + 1));
            }
        }
        
        Collections.sort(list);
        
        Collection<PriceDistribution> aRetColl = new ArrayList<PriceDistribution>();
        for(int i = 0; i < list.size(); i++) {
            PriceDistribution aPriceDist = new PriceDistribution();
            String priceStr = String.valueOf(list.get(i));
            aPriceDist.setPriceString(priceStr);
            aPriceDist.setCount(Integer.parseInt(map.get(priceStr)));
            aRetColl.add(aPriceDist);
        }
        
        logger.debug("SIZE="+aRetColl.size());
             
        return aRetColl;
    }   
}