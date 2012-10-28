package com.crawl.control;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import com.crawl.model.CrawlResultPackage;

/**
 * 
 * @author mschimpf
 *
 */
public class Crawler {
	/**
	 * 
	 * @return
	 */
	public Collection<CrawlResultPackage> crawlWebPages(){
		Collection<CrawlResultPackage> aReturnColl=new ArrayList<CrawlResultPackage>();
		Collection<CrawlResultPackage> aCurrentPageResults=null;
		int myIntPage=0;
		
		do{
			System.out.println("Page="+myIntPage);
			aCurrentPageResults=this.crawlWebPage(myIntPage);
			aReturnColl.addAll(aCurrentPageResults);
			
			myIntPage=myIntPage+100;
		} while (aCurrentPageResults.size()!=0 && myIntPage < 1000);
		
		return aReturnColl;
	}
	
	/**
	 * The crawl function
	 * @return
	 */
	private Collection<CrawlResultPackage> crawlWebPage(int page) {
		Collection<CrawlResultPackage> aReturnColl=new ArrayList<CrawlResultPackage>();
		URL url;
		InputStream is = null;
		DataInputStream dis;
		String line;

		try {
			// http://sfbay.craigslist.org/search/sya?query=&srchType=T&minAsk=1&maxAsk=100000&sort=pricedsc
			// http://sfbay.craigslist.org/search/sya?sort=pricedsc&hasPic=1&srchType=A
			String stringURL="http://sfbay.craigslist.org/search/sya?query=&srchType=T&minAsk=1&maxAsk=100000&sort=pricedsc&s="+page;
			System.out.println("stringURL"+stringURL);
			url = new URL(stringURL);

			is = url.openStream(); // throws an IOException
			dis = new DataInputStream(new BufferedInputStream(is));

			// Go to the complete html output. Line by line
			while ((line = dis.readLine()) != null) {
				line = line.trim(); // Cutoff unneeded characters
				// if a item is found
				if (line.matches(".*<a href=\"http://sfbay.craigslist.org/.*html.*>") == true) {
					CrawlResultPackage myTempCrawlResultPackage= new CrawlResultPackage();
					myTempCrawlResultPackage.setLine(line);
					// get the price
					myTempCrawlResultPackage.setPriceOfItem(this.getPriceFromString(line));
					aReturnColl.add(myTempCrawlResultPackage);
				}
			}
		} catch (MalformedURLException mue) {
			mue.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException ioe) {
				// nothing to see here
			}
		}
		
		System.out.println("Size="+aReturnColl.size());
		return aReturnColl;
	}
	
	/**
	 * Extracts the price as int value from Craigslists strings like this <a
	 * href="http://sfbay.craigslist.org/sby/sys/3256407578.html">$1895 - Apple
	 * MacBook Pro MC725LL/A 17-Inch</a
	 * 
	 * @param input
	 *            a complete string which must contains a $ sign as starting
	 *            position. The method reads all number signs after the $ and
	 *            transformed it into a int number
	 * @return
	 */
	public int getPriceFromString(String input) {
		try {
			String stringNumber = "";

			completeLoop: // Break out mark

			// Search for the first '$' character. 
			// Beginning from the left side
			for (int i = 0; i < input.length(); i++) {
				if (input.charAt(i) == '$') {
					// Get everything after the $ character if it is a number
					for (int j = i + 1; j < input.length(); j++) {
						if (Character.isDigit(input.charAt(j))) {
							char aChar = input.charAt(j);
							stringNumber = stringNumber + "" + aChar;
						} else {
							// No numbers left, break out
							break /* continue */completeLoop;
						}
					}
				}
			}

			// Transform the String to an int value and return it
			return new Integer(stringNumber).intValue();
		} catch (Exception e) {
			System.err.println(e.toString());
			return -1;
		}
	}	
}