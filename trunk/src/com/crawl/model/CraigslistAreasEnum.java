package com.crawl.model;

import org.apache.log4j.Logger;

import com.crawl.control.Crawler;

/**
 * Which area should be used to analyze?
 * @author mschimpf
 *
 */
public enum CraigslistAreasEnum {
	// The main area
	//MAIN_AREA_SF_BAY_AREA("sfbay"),
	MAIN_AREA_SF_BAY_AREA(""),
	
	// Sub areas of sfbay
	SAN_FRANCISCO("/sfc"),
	SOUTH_BAY("/sby"),
	EAST_BAY("/eby"),
	PENINSULA("/pen"),
	NORTH_BAY("/nby"),
	SANTA_CRUZ("/scz"),
	
	// Only for internal use
	URL_CONST_AREA_SF_BAY_AREA("sfbay");
	
	private String code;  
	
	private CraigslistAreasEnum(String c) {   
		this.code = c; 
	}  
	
	public String getCode() {   
		return this.code; 
	}
	
}