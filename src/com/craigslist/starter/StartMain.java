package com.craigslist.starter;

import java.io.IOException;
import java.util.Collection;
import java.util.Properties;

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
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// Set up a simple configuration that logs on the console.
	    BasicConfigurator.configure();
	    
	    Properties props = new Properties();
	    props.load(StartMain.class.getResourceAsStream("/log4j.properties"));
	    PropertyConfigurator.configure(props);
	    	    
	    logger.info("Entering application.");
	    	    
		Crawler aCrawl=new Crawler();
		
		Collection<CrawlResultPackage> aResultColl=aCrawl.crawlWebPages(CraigslistCategoryEnum.FOR_SALE__COMPUTER, "", 10 /*Max Offers*/);
		
		for (CrawlResultPackage myPackage:aResultColl){
			logger.info("myPackage Price="+myPackage.getPriceOfItem()+" Locations="+myPackage.getLocationsAsString());
		}
		
		logger.info("Ending application.");
	}
}