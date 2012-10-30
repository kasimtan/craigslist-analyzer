package com.craigslist.starter;

import java.util.Collection;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.crawl.control.Crawler;
import com.crawl.model.CraigslistCategoryEnum;
import com.crawl.model.CrawlResultPackage;

/**
 * 
 * @author mschimpf
 * 
 */
public class StartMain {
	// Define a static logger variable so that it references the
	// Logger instance named "MyApp".
	static Logger logger = Logger.getLogger(StartMain.class);
	
	/**
	 * Analyzing main function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// Set up a simple configuration that logs on the console.
	    BasicConfigurator.configure();
	    logger.info("Entering application.");
	    
	    // BasicConfigurator replaced with PropertyConfigurator.
	    PropertyConfigurator.configure("log4j.configuration");

		Crawler aCrawl=new Crawler();
		
		Collection<CrawlResultPackage> aResultColl=aCrawl.crawlWebPages(CraigslistCategoryEnum.FOR_SALE__COMPUTER, "", 10 /*Max Offers*/);
		
		for (CrawlResultPackage myPackage:aResultColl){
			logger.info("myPackage Price="+myPackage.getPriceOfItem()+" Locations="+myPackage.getLocationsAsString());
		}
		
		logger.info("Ending application.");
	}
}