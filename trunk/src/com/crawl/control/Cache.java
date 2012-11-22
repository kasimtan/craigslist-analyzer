package com.crawl.control;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class Cache {
    static Logger logger = Logger.getLogger(Cache.class);
    
    protected long creationTime=System.currentTimeMillis();
    protected long timeToLive=5000;   
    protected HashMap map=new HashMap();
    
    /**
     * 
     */
    protected synchronized void checkTimeToLife(){
        long currentTime=System.currentTimeMillis()-this.creationTime;
        
        logger.debug("currentTime="+currentTime);
 
        // If bigger then erase everything
        if (currentTime>this.timeToLive){
            logger.info("------------------ CACHE ERASED ------------------");
            this.map=new HashMap();
            this.creationTime=System.currentTimeMillis();
        }
    }
}