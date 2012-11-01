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
}
