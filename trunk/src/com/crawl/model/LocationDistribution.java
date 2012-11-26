package com.crawl.model;

/**
 * Location distribution modell data object.
 * @author Team Kappa
 *
 */
public class LocationDistribution {
    private String areaName;
    private int count;
    
    /**
     * GETTER/SETTER.
     * @return
     */
    public String getAreaName() {
        return areaName;
    }
    
    /**
     * GETTER/SETTER.
     * @param areaName
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    /**
     * GETTER/SETTER.
     * @return
     */
    public int getCount() {
        return count;
    }
    
    /**
     * GETTER/SETTER.
     * @param count
     */
    public void setCount(int count) {
        this.count = count;
    }
    
    /**
     * toString method.
     */
    @Override public String toString(){
        StringBuilder aRetBuilder=new StringBuilder();
        
        aRetBuilder.append("AreaName="+this.getAreaName()+"\n");
        aRetBuilder.append("Count="+this.getCount()+"\n");
        
        return aRetBuilder.toString();
    }
}