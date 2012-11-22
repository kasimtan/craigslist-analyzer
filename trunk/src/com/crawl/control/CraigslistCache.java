package com.crawl.control;

import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.crawl.model.CrawlResultPackage;

public class CraigslistCache extends Cache{
    static Logger logger = Logger.getLogger(CraigslistCache.class);
    
    private static CraigslistCache craigslistCache;
    
    private CraigslistCache(){}
    
    /**
     * SINGLETON getInstance method.
     * @return
     */
    public static CraigslistCache getInstance(){
        if (CraigslistCache.craigslistCache==null){
            CraigslistCache.craigslistCache=new CraigslistCache();
        }
        
        return CraigslistCache.craigslistCache;
    }
    
    public Collection<CrawlResultPackage> getResultFromCache(String inputKey){
        this.checkTimeToLife();
        
        logger.debug("getResultFromCache inputKey="+inputKey+"---------------------------------");
        
        Collection<CrawlResultPackage> aRetColl=(Collection<CrawlResultPackage>)this.map.get(inputKey);
        
        if (aRetColl != null && aRetColl.size()!=0){
            logger.debug("aRetColl.size="+aRetColl.size());
        } else {
            logger.debug("aRetColl IS NULL");
        }
        
        return aRetColl;
    }
    
    public void addResultToCache(String inputKey, Collection<CrawlResultPackage> inputCollection){
        this.checkTimeToLife();
        
        logger.debug("getResultFromCache inputKey="+inputKey+"++++++++++++++++++++++++++++++++++++++++++++");
        
        this.map.put(inputKey, inputCollection);
    }
}