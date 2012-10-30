package com.crawl.model;

import java.util.Collection;
import org.apache.log4j.Logger;

/**
 * 
 * @author mschimpf
 *
 */
public class CrawlResultPackage {
	static Logger logger = Logger.getLogger(CrawlResultPackage.class);
	
	private String line=null;
	private int priceOfItem=0;
	private Collection<String> locations=null;
	private String item=null;
	private String url=null;
	
	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getPriceOfItem() {
		return priceOfItem;
	}

	public void setPriceOfItem(int priceOfItem) {
		this.priceOfItem = priceOfItem;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public Collection<String> getLocations() {
		return locations;
	}

	public void setLocations(Collection<String> locations) {
		this.locations = locations;
	}
	
	public String getLocationsAsString(){
		if (this.getLocations()==null || this.getLocations().size()==0){
			return "";
		}
		
		String aReturnLocations="";
		
		for (String aLoc: this.getLocations()){
			aReturnLocations+=" "+aLoc;
		}
		
		logger.debug("aReturnLocations=|"+aReturnLocations+"|");
		
		return aReturnLocations;
	}
}