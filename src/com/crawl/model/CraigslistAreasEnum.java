package com.crawl.model;

/**
 * Which area should be used to analyze?
 * @author mschimpf
 *
 */
public enum CraigslistAreasEnum {
	// The main area
	MAIN_AREA_SF_BAY_AREA("sfbay"),
	
	// Sub areas of sfbay
	SAN_FRANCISCO("sfc"),
	SOUTH_BAY("sby"),
	EAST_BAY("eby"),
	PENINSULA("pen"),
	NORTH_BAY("nby"),
	SANTA_CRUZ("scz");
	
	private String code;  
	
	private CraigslistAreasEnum(String c) {   
		this.code = c; 
	}  
	
	public String getCode() {   
		return this.code; 
	}
}