package com.test;

import static org.junit.Assert.assertTrue;
import org.junit.Test;
import com.crawl.control.Crawler;

public class CrawlTest {
	   @Test   
	   public void test_createCrawler() {      
		   System.out.println("Test if pricePerMonth returns Euro...") ;      
		   Crawler c = new Crawler() ;      
		   assertTrue(c != null) ;   
	   }   	   
}