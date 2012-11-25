package com.crawl.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * 
 * @author mschimpf
 *
 */
public final class CrawlResultPackage {
	static final Logger logger = Logger.getLogger(CrawlResultPackage.class);
	
	private String line="";
	private int priceOfItem=0;
	private Collection<String> locations=new ArrayList<String>();
	private String item="";
	private String url="";
	
	public final String getItem() {
		return this.encodeAmpersand(item);
	}

	/**
	 * correct some encoding problems
	 * @param inputString
	 * @return
	 */
	private String encodeAmpersand(String inputString){
	    return inputString.replaceAll("&amp;", "&").replaceAll("&quot;", "'");
	}

	public final void setItem(String item) {
		this.item = item;
	}

	public final String getUrl() {
		return url;
	}

	public final void setUrl(String url) {
		this.url = url;
	}

	public final int getPriceOfItem() {
		return priceOfItem;
	}

	public final void setPriceOfItem(int priceOfItem) {
		this.priceOfItem = priceOfItem;
	}

	public final String getLine() {
		return line;
	}

	public final void setLine(String line) {
		this.line = line;
	}

	public final Collection<String> getLocations() {
		return locations;
	}

	public final void setLocations(Collection<String> locations) {
		this.locations = locations;
	}
	
	public final String getLocationsAsString(){
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
	
	/**
	 * Good looking result, and it uses StringBuilder which is faster and also thread save.
	 */
	@Override public final String toString(){
		try{
			StringBuilder aRetStringBuild=new StringBuilder();
			aRetStringBuild.append("----------------------------------------------------\n");
			aRetStringBuild.append("line=\t\t"+this.getLine()+"\n");
			aRetStringBuild.append("priceOfItem=\t"+this.getPriceOfItem()+"\n");
			aRetStringBuild.append("item=\t\t"+this.getItem()+"\n");
			aRetStringBuild.append("url=\t\t"+this.getUrl()+"\n");
			aRetStringBuild.append("locations=\t"+this.getLocationsAsString().trim()+"\n");
			aRetStringBuild.append("=====================================================\n");
			
			return aRetStringBuild.toString().trim();
		}catch (Exception e){
			this.logger.fatal(e);
			return e.toString();
		}
	}
}