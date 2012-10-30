package com.test;

import org.apache.log4j.Logger;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.crawl.control.Crawler;
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
		System.out.println("Create Crawler class...");
		Crawler c = new Crawler();
		assertTrue(c != null);
	}
}