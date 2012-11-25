package com.crawl.model;

public class PriceDistribution {
    private String priceString;
    private int count;
    
    public String getPriceString() {
        return priceString;
    }
    public void setPriceString(String priceString) {
        this.priceString = priceString;
    }
    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }
    
    @Override public String toString(){
        StringBuilder aRetBuilder=new StringBuilder();
        
        aRetBuilder.append("Price="+this.getPriceString()+"\n");
        aRetBuilder.append("Count="+this.getCount()+"\n");
        
        return aRetBuilder.toString();
    }
}