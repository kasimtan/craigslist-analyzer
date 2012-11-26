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
 * Crwaler testing methods.
 * @author Team Kappa
 * 
 */
public class CrawlTest {
	static Logger logger = Logger.getLogger(CrawlTest.class);

	@Test
	public void test_createCrawler() {
		logger.debug("Create Crawler class...");
		Crawler c = Crawler.getInstance();
		assertTrue(c != null);
	}
	
    @Test
    public void test_callGetWebPagesMethod() {
        logger.debug("get 100 apple items");
        
        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();

        String aUrl=aCrawl.createUrl(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER,
                CraigslistAreasEnum.PENINSULA, 
                "Apple");
               
        Collection<CrawlResultPackage> aCrawlResultColl=aCrawl.crawlWebPages(aUrl, 100);
        
        logger.debug("aCrawlResultColl.size()="+aCrawlResultColl.size());
        
        assertTrue(aCrawlResultColl.size()==100);
    }	
    
    @Test
    public void test_MatchPattern() {
        logger.debug("Test MatchPattern...");
        
        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();

        String aUrl=aCrawl.createUrl(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER,
                CraigslistAreasEnum.PENINSULA, 
                "Apple");
               
        Collection<CrawlResultPackage> aCrawlResultColl=aCrawl.crawlWebPages(aUrl, 100);

        String aString=aCrawl.getMatchPattern();
        
        logger.debug("MatchPatter="+aString);
        
        assertTrue(aString!=null);
    }
    
    @Test
    public void test_MatchPatternCompare() {
        logger.debug("Test MatchPattern... compare");

        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();

        String aUrl=aCrawl.createUrl(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER,
                CraigslistAreasEnum.PENINSULA, 
                "Apple");
               
        Collection<CrawlResultPackage> aCrawlResultColl=aCrawl.crawlWebPages(aUrl, 100);

        String aString=aCrawl.getMatchPattern();
        
        logger.debug("MatchPatter="+aString);
        
        assertTrue(aString.compareTo(".*<a href=\"http://sfbay.craigslist.org/.*html.*>")==0);
    }
    
    @Test
    public void test_MatchPatternCompareLength() {
        logger.debug("Test MatchPattern... compare length");
        
        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();

        String aUrl=aCrawl.createUrl(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER,
                CraigslistAreasEnum.PENINSULA, 
                "Apple");
               
        Collection<CrawlResultPackage> aCrawlResultColl=aCrawl.crawlWebPages(aUrl, 100);

        String aString=aCrawl.getMatchPattern();
        
        logger.debug("MatchPatter="+aString+" length="+aString.length());
        
        assertTrue(aString.length()==48);
    }
    
    @Test
    public void test_url1() {
        logger.debug("Test url 1");
        
        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();

        String aUrl=aCrawl.createUrl(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER,
                CraigslistAreasEnum.PENINSULA, 
                "Apple");
               
        Collection<CrawlResultPackage> aCrawlResultColl=aCrawl.crawlWebPages(aUrl, 100);
        
        logger.info("URL="+aUrl+" length="+aUrl.length());
        
        assertTrue(aUrl != null);
    }
    
    @Test
    public void test_url2() {
        logger.debug("Test url 2...");
        
        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();

        String aUrl=aCrawl.createUrl(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER,
                CraigslistAreasEnum.PENINSULA, 
                "Apple");
               
        Collection<CrawlResultPackage> aCrawlResultColl=aCrawl.crawlWebPages(aUrl, 100);
        
        logger.info("URL="+aUrl+" length="+aUrl.length());
        
        assertTrue(aUrl.compareTo("http://sfbay.craigslist.org/search/sya/pen?query=Apple&maxAsk=100000&sort=pricedsc&srchType=A&s=") ==0);    
    }
    
    @Test
    public void test_url3() {
        logger.debug("Test url 3...");
        
        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();

        String aUrl=aCrawl.createUrl(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER,
                CraigslistAreasEnum.PENINSULA, 
                "Apple");
               
        Collection<CrawlResultPackage> aCrawlResultColl=aCrawl.crawlWebPages(aUrl, 100);
                
        logger.debug("URL="+aUrl+" length="+aUrl.length());
        
        assertTrue(aUrl.length()==96);    
    }
}