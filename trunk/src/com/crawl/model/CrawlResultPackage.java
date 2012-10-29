package com.crawl.model;

import java.util.Collection;

/**
 * 
 * @author mschimpf
 *
 */
public class CrawlResultPackage {
	private String line=null;
	private int priceOfItem=0;
	private Collection<String> locations=null;
	
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
			aReturnLocations+=" - "+aLoc;
		}
		
		return aReturnLocations;
	}
}