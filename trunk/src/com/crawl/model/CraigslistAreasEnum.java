package com.crawl.model;

import org.apache.log4j.Logger;

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
	
	static Logger logger = Logger.getLogger(CraigslistAreasEnum.class);
	
	private String code;  
	
	private CraigslistAreasEnum(String c) {   
		this.code = c; 
	}  
	
	public String getCode() {   
		return this.code; 
	}
	
    public static CraigslistAreasEnum getArea(String inputString){
        logger.debug("inputString="+inputString);
        CraigslistAreasEnum[] aValuesOfCraigslistAreasEnum=CraigslistAreasEnum.values();
        
        for (CraigslistAreasEnum aFORCraigslistAreasEnum : aValuesOfCraigslistAreasEnum){
            logger.debug("aFORCraigslistAreasEnum.getCode()="+aFORCraigslistAreasEnum.getCode());
            
            if (aFORCraigslistAreasEnum.getCode().compareTo(inputString)==0){
                
                return aFORCraigslistAreasEnum;
            }
        }
        
        return null;
    }	
}