package com.crawl.model;

/**
 * Price distribution data model object.
 * @author Team Kappa
 *
 */
public class PriceDistribution {
    private String priceString;
    private int count;
    
    /**
     * GETTER/SETTER.
     * @return
     */
    public String getPriceString() {
        return priceString;
    }
    
    /**
     * GETTER/SETTER.
     * @param priceString
     */
    public void setPriceString(String priceString) {
        this.priceString = priceString;
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
     * toString method
     */
    @Override public String toString(){
        StringBuilder aRetBuilder=new StringBuilder();
        
        aRetBuilder.append("Price="+this.getPriceString()+"\n");
        aRetBuilder.append("Count="+this.getCount()+"\n");
        
        return aRetBuilder.toString();
    }
}