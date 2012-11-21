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
        logger.debug("get 100 apple items");
        
        Crawler c = new Crawler();
        
        c.crawlWebPages(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER, 
                CraigslistAreasEnum.PENINSULA, 
                "Apple", 100);
        
        Collection<CrawlResultPackage> aCrawlResultColl=c.getFindings();
        
        logger.debug("aCrawlResultColl.size()="+aCrawlResultColl.size());
        
        assertTrue(aCrawlResultColl.size()==100);
    }	
    
    @Test
    public void test_MatchPattern() {
        logger.debug("Test MatchPattern...");
        
        Crawler c = new Crawler();
        
        c.crawlWebPages(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER, 
                CraigslistAreasEnum.PENINSULA, 
                "Apple", 100);
        
        Collection<CrawlResultPackage> aCrawlResultColl=c.getFindings();

        String aString=c.getMatchPattern();
        
        logger.debug("MatchPatter="+aString);
        
        assertTrue(aString!=null);
    }
    
    @Test
    public void test_MatchPatternCompare() {
        logger.debug("Test MatchPattern... compare");
        
        Crawler c = new Crawler();
        
        c.crawlWebPages(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER, 
                CraigslistAreasEnum.PENINSULA, 
                "Apple", 100);
        
        Collection<CrawlResultPackage> aCrawlResultColl=c.getFindings();

        String aString=c.getMatchPattern();
        
        logger.debug("MatchPatter="+aString);
        
        assertTrue(aString.compareTo(".*<a href=\"http://sfbay.craigslist.org/.*html.*>")==0);
    }
    
    @Test
    public void test_MatchPatternCompareLength() {
        logger.debug("Test MatchPattern... compare length");
        
        Crawler c = new Crawler();
        
        c.crawlWebPages(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER, 
                CraigslistAreasEnum.PENINSULA, 
                "Apple", 100);
        
        Collection<CrawlResultPackage> aCrawlResultColl=c.getFindings();

        String aString=c.getMatchPattern();
        
        logger.debug("MatchPatter="+aString+" length="+aString.length());
        
        assertTrue(aString.length()==48);
    }
    
    @Test
    public void test_url1() {
        logger.debug("Test url 1");
        
        Crawler c = new Crawler();
        
        c.crawlWebPages(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER, 
                CraigslistAreasEnum.PENINSULA, 
                "Apple", 100);
        
        Collection<CrawlResultPackage> aCrawlResultColl=c.getFindings();
        
        String aStringUrl=c.getUrl();
        
        logger.info("URL="+aStringUrl+" length="+aStringUrl.length());
        
        assertTrue(aStringUrl != null);
    }
    
    @Test
    public void test_url2() {
        logger.debug("Test url 2...");
        
        Crawler c = new Crawler();
        
        c.crawlWebPages(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER, 
                CraigslistAreasEnum.PENINSULA, 
                "Apple", 100);
        
        Collection<CrawlResultPackage> aCrawlResultColl=c.getFindings();
        
        String aStringUrl=c.getUrl();
        
        logger.info("URL="+aStringUrl+" length="+aStringUrl.length());
        
        assertTrue(aStringUrl.compareTo("http://sfbay.craigslist.org/search/sya/pen?query=Apple&maxAsk=100000&sort=pricedsc&srchType=A&s=0") ==0);    
    }
    
    @Test
    public void test_url3() {
        logger.debug("Test url 3...");
        
        Crawler c = new Crawler();
        
        c.crawlWebPages(
                CraigslistCategoryEnum.FOR_SALE__COMPUTER, 
                CraigslistAreasEnum.PENINSULA, 
                "Apple", 100);
        
        Collection<CrawlResultPackage> aCrawlResultColl=c.getFindings();
        
        String aStringUrl=c.getUrl();
        
        logger.debug("URL="+aStringUrl+" length="+aStringUrl.length());
        
        assertTrue(aStringUrl.length()==97);    
    }
}