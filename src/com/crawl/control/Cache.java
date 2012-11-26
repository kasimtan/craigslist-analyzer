package com.crawl.control;

import java.util.Collection;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.crawl.model.CrawlResultPackage;

/**
 * Cache super class for attributes and methods which will be used by other implementations. For example, the EBayCache...
 * @author mschimpf
 *
 */
public class Cache {
    static Logger logger = Logger.getLogger(Cache.class);
    
    protected long creationTime=System.currentTimeMillis();
    protected long timeToLive=5000;   
    protected HashMap<String, Collection<CrawlResultPackage>> map=new HashMap<String, Collection<CrawlResultPackage>>();
    
    /**
     * Check if content is still usable? And if not drop the used data object and recreate a emptz new one. 
     */
    protected synchronized void checkTimeToLife(){
        long currentTime=System.currentTimeMillis()-this.creationTime;
        
        logger.debug("currentTime="+currentTime);
 
        // If bigger then erase everything
        if (currentTime>this.timeToLive){
            logger.info("------------------ CACHE ERASED ------------------");
            this.map=new HashMap<String, Collection<CrawlResultPackage>>();
            this.creationTime=System.currentTimeMillis();
        }
    }
}