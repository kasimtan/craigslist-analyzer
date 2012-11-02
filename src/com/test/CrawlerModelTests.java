package com.test;

import static org.junit.Assert.assertTrue;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CraigslistAreasEnum;
import com.crawl.model.CraigslistCategoryEnum;
import com.crawl.model.CrawlResultPackage;

/**
 * 
 * @author mschimpf
 *
 */
public class CrawlerModelTests {
	static Logger logger = Logger.getLogger(CrawlTest.class);

	@Test
	public void test_createCrawlerModellClasses() {
		System.out.println("Create Crawler modell classes...");
		
		CraigslistAlgorithmEnum a= CraigslistAlgorithmEnum.BEST;
		CraigslistAreasEnum b=CraigslistAreasEnum.EAST_BAY;
		CraigslistCategoryEnum c=CraigslistCategoryEnum.COMMUNITY__ACTIVITIES;
		CrawlResultPackage d= new CrawlResultPackage();
		
		assertTrue(a != null);
		assertTrue(b != null);
		assertTrue(c != null);
		assertTrue(d != null);
	}
	
    @Test
    public void test_2() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_3() {
        logger.debug("...");
        assertTrue(null == null);
    }
    
    @Test
    public void test_4() {
        logger.debug("...");
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
}
