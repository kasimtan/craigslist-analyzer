package com.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.analysis.control.AnaCrack;
import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CrawlResultPackage;
import com.crawl.model.CrawlResultPackageComparator;

/**
 * 
 * @author mschimpf
 *
 */
public class AnaCrackTest {
	static Logger logger = Logger.getLogger(CrawlTest.class);

	@Test
	public void test_createAnaCrack() {
		System.out.println("Create AnaCrack class...");
		AnaCrack c = new AnaCrack();
		assertTrue(c != null);
	}
	
    @Test
    public void test_illegalArgumentException() {
        logger.info("Catch expected IllegalArgumentException");
        logger.info("Create AnaCrack class and give over nothing null and -1 ...");
        AnaCrack c = new AnaCrack();
        
        try{
            Collection<CrawlResultPackage> aColl=c.getBestOffers(null, -1, null, -1, -1);
        } catch (IllegalArgumentException e){
            logger.info("Catch expected IllegalArgumentException exception");
            logger.info(e);
        }
 
        assertTrue(null == null);
    }
    
    @Test
    public void test_collectionAverage() {
        logger.info("Test Average");
        
        AnaCrack c = new AnaCrack();
        
        // ToDo try something lower then 10 Packages and you will receive an Exception
        Collection<CrawlResultPackage> aColl=c.getBestOffers(AnaCrackTest.generateFakeColl(1, 10, 1), 10, CraigslistAlgorithmEnum.BEST, 1, 10);

        logger.debug(""+c.toString());
        
        assertTrue(c.getAverage() == 5);
    }
    
    @Test
    public void test_Sort() {
        logger.debug("Test sort...");
        
        List<CrawlResultPackage> aCrawlResultPackage=new ArrayList<CrawlResultPackage>();
        
        CrawlResultPackage aCrawlResultPackage1=new CrawlResultPackage();
        aCrawlResultPackage1.setPriceOfItem(1000);
        aCrawlResultPackage.add(aCrawlResultPackage1);

        CrawlResultPackage aCrawlResultPackage2=new CrawlResultPackage();
        aCrawlResultPackage2.setPriceOfItem(1);
        aCrawlResultPackage.add(aCrawlResultPackage2);
        
        assertTrue(aCrawlResultPackage.size() == 2);
        
        Iterator<CrawlResultPackage> aIt=aCrawlResultPackage.iterator();
        
        CrawlResultPackage aCrawlResultPackageOne=aIt.next();
        assertTrue(aCrawlResultPackageOne.getPriceOfItem() == 1000);

        CrawlResultPackage aCrawlResultPackageTwo=aIt.next();
        assertTrue(aCrawlResultPackageTwo.getPriceOfItem() == 1);
    }
    
    @Test
    public void test_offers() {
        logger.info("Test offers");
        
        AnaCrack c = new AnaCrack();
        
        // ToDo try something lower then 10 Packages and you will receive an Exception
        Collection<CrawlResultPackage> aColl=c.getBestOffers(AnaCrackTest.generateFakeColl(1, 10, 1), 10, CraigslistAlgorithmEnum.BEST, 1, 10);

        logger.debug(""+c.toString());
        
        assertTrue(c.getOffers() == 9);
    }
    
    @Test
    public void test_min() {
        logger.info("Test min");
        
        AnaCrack c = new AnaCrack();
        
        // ToDo try something lower then 10 Packages and you will receive an Exception
        Collection<CrawlResultPackage> aColl=c.getBestOffers(AnaCrackTest.generateFakeColl(1, 10, 1), 10, CraigslistAlgorithmEnum.BEST, 1, 10);

        logger.debug(""+c.toString());
        
        assertTrue(c.getMin() == 1);
    }
    
    @Test
    public void test_max() {
        logger.info("Test max");
        
        AnaCrack c = new AnaCrack();
        
        // ToDo try something lower then 10 Packages and you will receive an Exception
        Collection<CrawlResultPackage> aColl=c.getBestOffers(AnaCrackTest.generateFakeColl(1, 10, 1), 10, CraigslistAlgorithmEnum.BEST, 1, 10);

        logger.debug(""+c.toString());
        
        assertTrue(c.getMax() == 9);
    }
    
    @Test
    public void test_median() {
        logger.info("Test median");
        
        AnaCrack c = new AnaCrack();
        
        // ToDo try something lower then 10 Packages and you will receive an Exception
        Collection<CrawlResultPackage> aColl=c.getBestOffers(AnaCrackTest.generateFakeColl(1, 10, 1), 10, CraigslistAlgorithmEnum.BEST, 1, 10);

        logger.debug(""+c.toString());
        
        assertTrue(c.getMedian() == 5.0);
    }
    
    @Test
    public void test_standardDeviation() {
        logger.info("Test Standard Deviation");
        
        AnaCrack c = new AnaCrack();
        
        // ToDo try something lower then 10 Packages and you will receive an Exception
        Collection<CrawlResultPackage> aColl=c.getBestOffers(AnaCrackTest.generateFakeColl(1, 10, 1), 10, CraigslistAlgorithmEnum.BEST, 1, 10);

        logger.debug(""+c.toString());
        
        assertTrue(c.getStandardDeviation() == 2.7386127875258306);
    }
        
    /**
     * Utility method to produce test objects.
     * @param inputHowMuchPackages
     * @param inputPrice
     * @return
     */
    private static Collection<CrawlResultPackage> generateFakeColl(
            int inputFromPrice,
            int inputToPrice,
            int inputSteps){
        Collection<CrawlResultPackage> aRetColl=new ArrayList<CrawlResultPackage>();
        
        for (int i=inputFromPrice;i<inputToPrice;i=i+inputSteps){
            CrawlResultPackage aPack=new CrawlResultPackage();
            aPack.setItem("PC Laptop &amp; Macbook Repairs System Software &amp; Data Recovery Start from");
            aPack.setLine("<a href=\"http://sfbay.craigslist.org/sfc/syd/3377230486.html\">$29 - PC Laptop &amp; Macbook Repairs System Software &amp; Data Recovery Start from</a>");
            Collection<String> aStringColl=new ArrayList<String>();
            aStringColl.add("downtown");
            aStringColl.add("civic");
            aStringColl.add("van ness");
            aPack.setLocations(aStringColl);
            aPack.setPriceOfItem(i);
            aPack.setUrl("http://sfbay.craigslist.org/sfc/syd/3377230486.html");  
            logger.debug(aPack.toString());
            aRetColl.add(aPack);
        }
        
        return aRetColl;
    }
}
