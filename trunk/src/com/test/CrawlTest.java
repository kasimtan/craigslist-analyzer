package com.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.crawl.control.Crawler;
import com.crawl.model.CraigslistAreasEnum;
import com.crawl.model.CraigslistCategoryEnum;
import com.crawl.model.CrawlResultPackage;

/**
 * 
 * @author mschimpf
 * 
 */
public class CrawlTest {
	static Logger logger = Logger.getLogger(CrawlTest.class);

	@Test
	public void test_createCrawler() {
		logger.debug("Create Crawler class...");
		Crawler c = new Crawler();
		assertTrue(c != null);
	}
	
    @Test
    public void test_callGetWebPagesMethod() {
        logger.debug("...");
        
        Crawler c = new Crawler();
        
        Collection<CrawlResultPackage> aCrawlResultColl=c.crawlWebPages(CraigslistCategoryEnum.FOR_SALE__COMPUTER, CraigslistAreasEnum.PENINSULA, "Apple", 100);
        assertTrue(null == null);
        assertTrue(aCrawlResultColl.size()!=0);
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