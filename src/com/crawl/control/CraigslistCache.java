package com.crawl.control;

import java.util.Collection;

import org.apache.log4j.Logger;

import com.crawl.model.CrawlResultPackage;

/**
 * Craigslist cache is a cache object only for the content of Craigslist. For additional web pages will be additional cache classes created.
 * @author Kappa Team
 *
 */
public class CraigslistCache extends Cache{
    static Logger logger = Logger.getLogger(CraigslistCache.class);
    
    private static CraigslistCache craigslistCache;
    
    /**
     * Singleton private constructore.
     */
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
    
    /**
     * Get info from cache.
     * @param inputKey
     * @return
     */
    public Collection<CrawlResultPackage> getResultFromCache(String inputKey){
        this.checkTimeToLife();
        
        logger.debug("getResultFromCache inputKey="+inputKey+"---------------------------------");
        
        Collection<CrawlResultPackage> aRetColl=this.map.get(inputKey);
        
        if (aRetColl != null && aRetColl.size()!=0){
            logger.debug("aRetColl.size="+aRetColl.size());
        } 
        
        return aRetColl;
    }
    
    /**
     * Add info to cache.
     * @param inputKey
     * @param inputCollection
     */
    public void addResultToCache(String inputKey, Collection<CrawlResultPackage> inputCollection){
        this.checkTimeToLife();
        
        logger.debug("getResultFromCache inputKey="+inputKey+"++++++++++++++++++++++++++++++++++++++++++++");
        
        this.map.put(inputKey, inputCollection);
    }
}