package com.crawl.model;

/**
 * 
 * @author mschimpf
 *
 */
public class CrawlResultPackage {
	public String line=null;
	int priceOfItem=0;
	
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
}