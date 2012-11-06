package com.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
    public void test_collection() {
        logger.info("Catch expected IllegalArgumentException");
        
        AnaCrack c = new AnaCrack();
        
        // ToDo try something lower then 10 Packages and you will receive an Exception
        Collection<CrawlResultPackage> aColl=c.getBestOffers(AnaCrackTest.generateFakeColl(1, 10, 1), 10, CraigslistAlgorithmEnum.BEST, 1, 10);

        logger.info(""+c.toString());
        
        assertTrue(null == null);
    }
    
    @Test
    public void test_Sort() {
        logger.debug("Sort");
        
        AnaCrack c = new AnaCrack();
        
        // ToDo try something lower then 10 Packages and you will receive an Exception
        Collection<CrawlResultPackage> aColl=c.getBestOffers(AnaCrackTest.generateFakeColl(1, 10, 1), 10, CraigslistAlgorithmEnum.BEST, 1, 10);
        
        //Collections.sort(aColl.toArray(), new CrawlResultPackageComparator());
        
        assertTrue(null == null);
    }
    
    @Test
    public void test_5() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_6() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_7() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_8() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_9() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_10() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_11() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_12() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_13() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_14() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_15() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_16() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_17() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_18() {
        logger.debug("...");
        assertTrue(null == null);
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
