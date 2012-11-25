package com.crawl.model;

public class LocationDistribution {
    private String areaName;
    private int count;
    
    public String getAreaName() {
        return areaName;
    }
    
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    
    @Override public String toString(){
        StringBuilder aRetBuilder=new StringBuilder();
        
        aRetBuilder.append("AreaName="+this.getAreaName()+"\n");
        aRetBuilder.append("Count="+this.getCount()+"\n");
        
        return aRetBuilder.toString();
    }
}