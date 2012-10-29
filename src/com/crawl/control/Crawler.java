package com.crawl.control;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;

import com.crawl.model.CraigslistAreasEnum;
import com.crawl.model.CraigslistCategoryEnum;
import com.crawl.model.CraigslistCategoryEnum;
import com.crawl.model.CrawlResultPackage;

/**
 * 
 * @author mschimpf
 *
 */
public class Crawler {
	/**
	 * 
	 * @param inCraigslistCategoryEnum Which category?
	 * @param inSearchItem Which item do you search "iPhone", "Apple IIc", etc.
	 * @return
	 */
	public Collection<CrawlResultPackage> crawlWebPages(CraigslistCategoryEnum inCraigslistCategoryEnum, String inSearchItem){
		Collection<CrawlResultPackage> aReturnColl=new ArrayList<CrawlResultPackage>();
		Collection<CrawlResultPackage> aCurrentPageResults=null;
		int myIntPage=0;
		
		do{
			//System.out.println("Page="+myIntPage);
			aCurrentPageResults=this.crawlWebPage(myIntPage, inCraigslistCategoryEnum, inSearchItem);
			aReturnColl.addAll(aCurrentPageResults);
			
			myIntPage=myIntPage+100;
		} while (aCurrentPageResults.size()!=0 && myIntPage < 1000);
		
		return aReturnColl;
	}
	
	/**
	 * The crawl function
	 * @return
	 */
	private Collection<CrawlResultPackage> crawlWebPage(int page, CraigslistCategoryEnum inEnumForSaleTopic, String inSearchItem) {
		Collection<CrawlResultPackage> aReturnColl=new ArrayList<CrawlResultPackage>();
		URL url;
		InputStream is = null;
		DataInputStream dis;
		String line;

		try {
			// OLD http://sfbay.craigslist.org/search/sya?query=&srchType=T&minAsk=1&maxAsk=100000&sort=pricedsc
			// OLD http://sfbay.craigslist.org/search/sya?sort=pricedsc&hasPic=1&srchType=A
			
			// http://sfbay.craigslist.org/search/sya?query=&srchType=T&minAsk=1&maxAsk=100000&sort=pricedsc&s=0
			// http://sfbay.craigslist.org/search/sya?
			// 	maxAsk=1000000
			// 	&sort=pricedsc
			//  &srchType=A
			String stringURL="http://"+CraigslistAreasEnum.MAIN_AREA_SF_BAY_AREA.getCode()+
					".craigslist.org/search/"+inEnumForSaleTopic.getCode()+
					"?maxAsk=100000&sort=pricedsc&srchType=A&s="+page;
			//System.out.println("URL = "+stringURL);
			url = new URL(stringURL);

			is = url.openStream(); // throws an IOException
			dis = new DataInputStream(new BufferedInputStream(is));

			// Go to the complete html output. Line by line
			while ((line = dis.readLine()) != null) {
				
				// A line looks like this...
				// Oct 26 - $799999 - COLD CASH CASH FOR ANY USED/NEW IPHONES - IPADS - (santa clara) img computers - by owner 
				
				line = line.trim(); // Cutoff unneeded characters
				// if a item is found
				
				// <a href="http://sfbay.craigslist.org/sby/sys/3358383668.html">$20000 - F5 big-IP 6900 series obo</a>
				// if (line.matches(".*<a href=\"http://"+CraigslistAreasEnum.MAIN_AREA_SF_BAY_AREA+".craigslist.org/.*html.*>") == true) {
				String aStringMatch=".*<a href=\"http://"+CraigslistAreasEnum.MAIN_AREA_SF_BAY_AREA.getCode()+".craigslist.org/.*html.*>";
				
				//System.out.println("aStringMatch="+aStringMatch);
				
				if (line.matches(aStringMatch) == true) {
					CrawlResultPackage myTempCrawlResultPackage= new CrawlResultPackage();
					myTempCrawlResultPackage.setLine(line);
					// get the price
					myTempCrawlResultPackage.setPriceOfItem(this.getPriceFromString(line));
					
					// The location is located in the html source code two lines under the a href
					line = dis.readLine();
					line = dis.readLine();
					myTempCrawlResultPackage.setLocations(this.getLocationsFromString(line));
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
		
		//System.out.println("Size="+aReturnColl.size());
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
	private int getPriceFromString(String input) {
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
	
	/**
	 * 
	 * @param input
	 * @return
	 */
	private Collection<String> getLocationsFromString(String input) {
		try {
			//System.out.println("input="+input);
			
			Collection<String> aCollLoc=new ArrayList<String>();
			String aWorkString=null;

			completeLoop: // Break out mark

			// Search for the first '$' character. 
			// Beginning from the left side
			for (int i = input.length()-1; i >= 0 ; i--) {
				if (input.charAt(i) == '(') {
					// Get everything after the ( character
					aWorkString=input.substring(i+1);
					
					// Cut off the rest including and after the ')' sign
					for (int j=0;j<aWorkString.length();j++){
						if (aWorkString.charAt(j) == ')') {
							aWorkString=aWorkString.substring(0, j);							
						}
					}
					
					//System.out.println("aWorkString="+aWorkString);
					if (aWorkString.contains("/")==true){
						Collection<String> aStringCollLocations=new ArrayList<String>();
						aCollLoc.addAll(this.getLocationsFromCleanStringRecursion(aWorkString, aStringCollLocations, 5));
						break completeLoop;
					} else {
						aCollLoc.add(aWorkString);
						break completeLoop;
					}
				}
			}

			return aCollLoc;
		} catch (Exception e) {
			System.err.println(e.toString());
			return new ArrayList<String>();
		}
	}	
	
	/**
	 * Split up the writing style "dublin / pleasanton / livermore"
	 * @param input
	 * @return
	 */
	private Collection<String> getLocationsFromCleanStringRecursion(String inputCleanString, Collection<String> ínputCollection, int inMaxRecursion) {
		try {		
			// input string look like that = Locations=dublin / pleasanton / livermore
			//System.out.println("inputCleanString=|"+inputCleanString+"|");
			
			// Reduce maxRecursion
			inMaxRecursion--;
			
			if (inputCleanString.contains("/")==true){
				for (int i=0;i<inputCleanString.length();i++){
					if (inputCleanString.charAt(i) == '/'){
						String aTempStringNewAddItem=inputCleanString.substring(0, i).trim();
						String aTempStringNewRecurItem=inputCleanString.substring(i+1).trim();
						
						//System.out.println("lllllllllllllllllllllll aTempStringNewAddItem=|"+aTempStringNewAddItem+"| aTempStringNewRecurItem=|"+aTempStringNewRecurItem+"|");
						
						// Break out condition
						if (inMaxRecursion<=0){
							ínputCollection.add(inputCleanString);
							return ínputCollection;
						} else {
							ínputCollection.add(aTempStringNewAddItem);
							this.getLocationsFromCleanStringRecursion(aTempStringNewRecurItem, ínputCollection, inMaxRecursion);
						}
					}
				}
			} else {
				ínputCollection.add(inputCleanString.trim());
			}
		
			return ínputCollection;
		} catch (Exception e) {
			System.err.println(e.toString());
			ínputCollection.add(inputCleanString);
			return ínputCollection;
		}
	}
}