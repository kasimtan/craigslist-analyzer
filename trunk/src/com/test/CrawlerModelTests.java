package com.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CraigslistAreasEnum;
import com.crawl.model.CraigslistCategoryEnum;
import com.crawl.model.CrawlResultPackage;
import com.crawl.model.CrawlResultPackageComparator;

/**
 * 
 * @author mschimpf
 *
 */
public class CrawlerModelTests {
	static Logger logger = Logger.getLogger(CrawlTest.class);

	@Test
	public void test_createCrawlResultPackages() {
		logger.debug("Create Crawler modell classes...");
		
		CrawlResultPackage d= new CrawlResultPackage();
		
		assertTrue(d != null);
	}
	
    @Test
    public void test_CraigslistAlgorithmEnum() {
        logger.debug("Create Crawler modell classes...CraigslistAlgorithmEnum");
        
        CraigslistAlgorithmEnum a= CraigslistAlgorithmEnum.BEST;
        
        assertTrue(a != null);
    }
    
    @Test
    public void test_CraigslistAreasEnum() {
        logger.debug("Create Crawler modell classes...CraigslistAreasEnum");
        
        CraigslistAreasEnum b=CraigslistAreasEnum.EAST_BAY;
        assertTrue(b != null);
    }
    
    @Test
    public void test_CraigslistCategoryEnum() {
        logger.debug("Create Crawler modell classes...CraigslistCategoryEnum");
        
        CraigslistCategoryEnum c=CraigslistCategoryEnum.COMMUNITY__ACTIVITIES;
        assertTrue(c != null);
    }

    @Test
    public void test_CrawlResultPackage_Price() {
        logger.debug("Test CrawlResultPackage...price");
        
        CrawlResultPackage d= new CrawlResultPackage();
        d.setPriceOfItem(1000);
        
        assertTrue(d.getPriceOfItem() == 1000);
    }
    
    @Test
    public void test_CrawlResultPackage_item() {
        logger.debug("Test CrawlResultPackage...item");
        
        CrawlResultPackage d= new CrawlResultPackage();
        d.setItem("A");
        
        assertTrue(d.getItem().compareTo("A") == 0);
    }
    
    @Test
    public void test_CrawlResultPackage_line() {
        logger.debug("Test CrawlResultPackage...line");
        
        CrawlResultPackage d= new CrawlResultPackage();
        d.setLine("B");
        
        assertTrue(d.getLine().compareTo("B") == 0);
    }
    
    @Test
    public void test_CrawlResultPackage_locations() {
        logger.debug("Test CrawlResultPackage...locations");
        
        CrawlResultPackage d= new CrawlResultPackage();

        Collection<String> aCollString=new ArrayList<String>();
        aCollString.add("One");
        
        d.setLocations(aCollString);

        Collection<String> aRetColl=d.getLocations();
        
        assertTrue(aRetColl!=null);
        assertTrue(aRetColl.size()==1);
        
        for (String aString:aRetColl){
            assertTrue(aString.compareTo("One")==0);
        }
    }
    
    @Test
    public void test_CrawlResultPackage_url() {
        logger.debug("Test CrawlResultPackage...url");
        
        CrawlResultPackage d= new CrawlResultPackage();
        d.setUrl("www.scu.edu");
        
        assertTrue(d.getUrl().compareTo("www.scu.edu") == 0);
    }
    
    @Test
    public void test_CrawlResultPackage_toString() {
        logger.debug("Test CrawlResultPackage...toString");
        
        CrawlResultPackage d= new CrawlResultPackage();
        d.setItem("A");
        d.setLine("B");
        Collection<String> aCollString=new ArrayList<String>();
        aCollString.add("One");
        
        d.setLocations(aCollString);
        d.setPriceOfItem(1000);
        d.setUrl("www.scu.edu");
                
        String aToString=d.toString();
        
        logger.debug("aToString="+aToString+" length="+aToString.length());
        
        assertTrue(d.toString().length() == 175);
    }
    
    @Test
    public void test_CrawlResultPackageComparator() {
        logger.debug("CrawlResultPackageComparator");
        
        CrawlResultPackageComparator aCrawlResultPackageComparator=new CrawlResultPackageComparator();
        
        assertTrue(aCrawlResultPackageComparator != null);
    }
        
    @Test
    public void test_comparator() {
        logger.debug("Test comparator...");
        
        List<CrawlResultPackage> aCrawlResultPackage=new ArrayList<CrawlResultPackage>();
        
        CrawlResultPackageComparator aCrawlResultPackageComparator=new CrawlResultPackageComparator();
        Collections.sort(aCrawlResultPackage, aCrawlResultPackageComparator);
        
        assertTrue(aCrawlResultPackageComparator != null);
    }
    
    @Test
    public void test_comparator2() {
        logger.debug("Test comparator...");
        
        List<CrawlResultPackage> aCrawlResultPackage=new ArrayList<CrawlResultPackage>();
        
        CrawlResultPackageComparator aCrawlResultPackageComparator=new CrawlResultPackageComparator();
        Collections.sort(aCrawlResultPackage, aCrawlResultPackageComparator);
        
        assertTrue(aCrawlResultPackage.size() == 0);
    }
    
    @Test
    public void test_comparator24() {
        logger.debug("Test comparator...");
        
        List<CrawlResultPackage> aCrawlResultPackage=new ArrayList<CrawlResultPackage>();
        
        CrawlResultPackage aCrawlResultPackage1=new CrawlResultPackage();
        aCrawlResultPackage1.setPriceOfItem(1000);
        aCrawlResultPackage.add(aCrawlResultPackage1);

        CrawlResultPackage aCrawlResultPackage2=new CrawlResultPackage();
        aCrawlResultPackage2.setPriceOfItem(1);
        aCrawlResultPackage.add(aCrawlResultPackage2);

        CrawlResultPackageComparator aCrawlResultPackageComparator=new CrawlResultPackageComparator();
        Collections.sort(aCrawlResultPackage, aCrawlResultPackageComparator);
        
        logger.info("aCrawlResultPackage.size()="+aCrawlResultPackage.size());
        assertTrue(aCrawlResultPackage.size() == 2);
    }
    
    @Test
    public void test_comparator3() {
        logger.debug("Test comparator...3");
        
        List<CrawlResultPackage> aCrawlResultPackage=new ArrayList<CrawlResultPackage>();
        
        CrawlResultPackage aCrawlResultPackage1=new CrawlResultPackage();
        aCrawlResultPackage1.setPriceOfItem(1000);
        aCrawlResultPackage.add(aCrawlResultPackage1);

        CrawlResultPackage aCrawlResultPackage2=new CrawlResultPackage();
        aCrawlResultPackage2.setPriceOfItem(1);
        aCrawlResultPackage.add(aCrawlResultPackage2);
        
        assertTrue(aCrawlResultPackage.size() == 2);
        
        CrawlResultPackageComparator aCrawlResultPackageComparator=new CrawlResultPackageComparator();
        Collections.sort(aCrawlResultPackage, aCrawlResultPackageComparator);        
        
        Iterator<CrawlResultPackage> aIt=aCrawlResultPackage.iterator();
        
        CrawlResultPackage aCrawlResultPackageOne=aIt.next();
        assertTrue(aCrawlResultPackageOne.getPriceOfItem() == 1);

        CrawlResultPackage aCrawlResultPackageTwo=aIt.next();
        assertTrue(aCrawlResultPackageTwo.getPriceOfItem() == 1000);
    }
    
    @Test
    public void test_comparator4() {
        logger.debug("Test comparator...4");
        
        List<CrawlResultPackage> aCrawlResultPackage=new ArrayList<CrawlResultPackage>();
        
        CrawlResultPackage aCrawlResultPackage1=new CrawlResultPackage();
        aCrawlResultPackage1.setPriceOfItem(1000);
        aCrawlResultPackage.add(aCrawlResultPackage1);

        CrawlResultPackage aCrawlResultPackage2=new CrawlResultPackage();
        aCrawlResultPackage2.setPriceOfItem(1);
        aCrawlResultPackage.add(aCrawlResultPackage2);
        
        assertTrue(aCrawlResultPackage.size() == 2);
        
        Iterator<CrawlResultPackage> aIt=aCrawlResultPackage.iterator();
        
        CrawlResultPackage aCrawlResultPackageOne=aIt.next();
        assertTrue(aCrawlResultPackageOne.getPriceOfItem() == 1000);

        CrawlResultPackage aCrawlResultPackageTwo=aIt.next();
        assertTrue(aCrawlResultPackageTwo.getPriceOfItem() == 1);
    }    
}