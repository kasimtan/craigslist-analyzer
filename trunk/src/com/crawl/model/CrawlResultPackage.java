package com.crawl.model;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;

/**
 * The extraction item data object from Crawl object. 
 * @author Team Kappa
 *
 */
public final class CrawlResultPackage {
	static final Logger logger = Logger.getLogger(CrawlResultPackage.class);
	
	private String line="";
	private int priceOfItem=0;
	private Collection<String> locations=new ArrayList<String>();
	private String item="";
	private String url="";
	
	/**
	 * GETTER/SETTER.
	 * @return
	 */
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

	/**
	 * GETTER/SETTER.
	 * @param item
	 */
	public final void setItem(String item) {
		this.item = item;
	}

	/**
	 * GETTER/SETTER.
	 * @return
	 */
	public final String getUrl() {
		return url;
	}

	/**
	 * GETTER/SETTER.
	 * @param url
	 */
	public final void setUrl(String url) {
		this.url = url;
	}

	/**
	 * GETTER/SETTER.
	 * @return
	 */
	public final int getPriceOfItem() {
		return priceOfItem;
	}

	/**
	 * GETTER/SETTER.
	 * @param priceOfItem
	 */
	public final void setPriceOfItem(int priceOfItem) {
		this.priceOfItem = priceOfItem;
	}

	/**
	 * GETTER/SETTER.
	 * @return
	 */
	public final String getLine() {
		return line;
	}

	/**
	 * GETTER/SETTER.
	 * @param line
	 */
	public final void setLine(String line) {
		this.line = line;
	}

	/**
	 * GETTER/SETTER.
	 * @return
	 */
	public final Collection<String> getLocations() {
		return locations;
	}

	/**
	 * GETTER/SETTER.
	 * @param locations
	 */
	public final void setLocations(Collection<String> locations) {
		this.locations = locations;
	}
	
	/**
	 * Simply print method.
	 * @return
	 */
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