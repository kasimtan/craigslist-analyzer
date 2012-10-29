package com.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.crawl.control.Crawler;

/**
 * 
 * @author mschimpf
 *
 */
public class CrawlTest {
	   @Test   
	   public void test_createCrawler() {      
		   System.out.println("Create Crawler class...") ;      
		   Crawler c = new Crawler() ;      
		   assertTrue(c != null) ;   
	   }   	   
}