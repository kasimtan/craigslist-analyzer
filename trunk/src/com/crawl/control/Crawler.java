package com.crawl.control;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawl.model.CraigslistAreasEnum;
import com.crawl.model.CraigslistCategoryEnum;
import com.crawl.model.CrawlResultPackage;

/**
 * SINGLETON: The main crawler method to extract the oinformation from the Craiglists web page.
 * @author Team Kappa
 *
 */
public class Crawler {
    static Logger logger = Logger.getLogger(Crawler.class);
    
    private static Crawler crawler;
    
    //private String url;
    private String matchPattern;
    
    /**
     * Private constructor (SINGLETON)
     */
    private Crawler(){}
    
    /**
     * SINGLETON getInstance method.
     * @return
     */
    public static Crawler getInstance(){
        if (Crawler.crawler==null){
            Crawler.crawler=new Crawler();
        }
        
        return Crawler.crawler;
    }
    
    /**
     * The main extraction method, to call the Craigslist page and get all needed information from there.
     * 
     * @param inCraigslistCategoryEnum Which category?
     * @param inCraigslistAreasEnum Which area
     * @param inSearchItem Which item do you search "iPhone", "Apple IIc", etc.
     * @param inputIntOffers how many offers 100 = 1 Craigslist page
     * @return
     */
    public synchronized Collection<CrawlResultPackage> crawlWebPages(
            String inputSearchUrl,
            int inputIntOffers){
        
        Collection<CrawlResultPackage> aCurrentPageResults=null;
        int myIntPage=0;
        
        // Do we already searched for this URL?
        aCurrentPageResults=CraigslistCache.getInstance().getResultFromCache(inputSearchUrl);
        
        // No! Then do the search
        if (aCurrentPageResults==null){
            Collection<CrawlResultPackage> aReturnColl=new ArrayList<CrawlResultPackage>();
            
            do {
                logger.debug("Page="+myIntPage);
            
                aCurrentPageResults=this.crawlWebPage(inputSearchUrl, myIntPage);
                aReturnColl.addAll(aCurrentPageResults);
            
                myIntPage=myIntPage+100;
            } while (aCurrentPageResults.size()!=0 && myIntPage < inputIntOffers);
            
            // And add it to the cache object
            CraigslistCache.getInstance().addResultToCache(inputSearchUrl, aReturnColl);            
            
            return aReturnColl;
        } else {
            return aCurrentPageResults;
        }
    }
    
    /**
     * This is an important step because we need the Craigslist URL before we search also as key for the cache object.
     * (Specified for only San Francisco Bay Area).
     * 
     * @param inCraigslistCategoryEnum
     * @param inCraigslistAreasEnum
     * @param inSearchItem
     * @return
     */
    public String createUrl(CraigslistCategoryEnum inCraigslistCategoryEnum, 
            CraigslistAreasEnum inCraigslistAreasEnum,
            String inSearchItem) {
        String aTempUrl=(
                "http://"+CraigslistAreasEnum.URL_CONST_AREA_SF_BAY_AREA.getCode()+
                ".craigslist.org/search/"+inCraigslistCategoryEnum.getCode()+
                inCraigslistAreasEnum.getCode()+
                "?query="+inSearchItem+
                "&maxAsk=100000&sort=pricedsc&srchType=A&s=").trim();        
        
        return aTempUrl;
    }
    
    /**
     * This is an important step because we need the Craigslist URL before we search also as key for the cache object.
     * (For global worldwide location/area)
     * 
     * @param inCraigslistCategoryCode
     * @param inCraigslistAreasURL
     * @param inSearchItem
     * @return
     */
    public String createUrl(String inCraigslistCategoryCode, 
            String inCraigslistAreasURL,
            String inSearchItem) {
        try {
            inSearchItem = URLEncoder.encode(inSearchItem,"UTF-8");
        }
        catch(UnsupportedEncodingException e) { }
        String aTempUrl=(
                inCraigslistAreasURL+
                "/search/"+inCraigslistCategoryCode+
                "?query="+inSearchItem+
                "&sort=priceasc&srchType=T&s=").trim();        
        
        return aTempUrl;
    }
    
    /**
     * The crawl function for one page of Craigslist.
     * @return
     */
    private synchronized Collection<CrawlResultPackage> crawlWebPage(String inputSearchUrl, int page) {
        Collection<CrawlResultPackage> aReturnColl=new ArrayList<CrawlResultPackage>();

        try {
            // OLD http://sfbay.craigslist.org/search/sya?query=&srchType=T&minAsk=1&maxAsk=100000&sort=pricedsc
            // OLD http://sfbay.craigslist.org/search/sya?sort=pricedsc&hasPic=1&srchType=A
            
            // http://sfbay.craigslist.org/search/sya?query=&srchType=T&minAsk=1&maxAsk=100000&sort=pricedsc&s=0
            // http://sfbay.craigslist.org/search/sya?
            //  maxAsk=1000000
            //  &sort=pricedsc
            //  &srchType=A
            logger.info("URL = "+inputSearchUrl+" +page="+page);
            //url = new URL(inputSearchUrl+page);
            inputSearchUrl = inputSearchUrl + page;
            
            // Get items list
            Document document = Jsoup.connect(inputSearchUrl).get();
            Elements rows = document.select("p.row");
            for(Element row : rows) {
                CrawlResultPackage myTempCrawlResultPackage= new CrawlResultPackage();
                myTempCrawlResultPackage.setLine(row.html());
                // get price
                myTempCrawlResultPackage.setPriceOfItem(getPriceFromString(row.select("span.price").text()));
                // Get item and url
                myTempCrawlResultPackage.setItem(row.select("span.pl a").text());
                myTempCrawlResultPackage.setUrl(row.select("span.pl a").attr("abs:href"));
                // Get location
                myTempCrawlResultPackage.setLocations(getLocationsFromString(row.select("small").text()));
                aReturnColl.add(myTempCrawlResultPackage);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        logger.debug("Size="+aReturnColl.size());
        return aReturnColl;
    }
    
    /**
     * Extracts the price as int value from Craigslists strings like this <a
     * href="http://sfbay.craigslist.org/sby/sys/3256407578.html">$1895 - Apple
     * MacBook Pro MC725LL/A 17-Inch</a
     * 
     * @param input
     *            a complete string which must contains a > sign as starting
     *            position. The method reads all number signs after the > and
     *            transformed it into a int number
     * @return
     */
    private int getPriceFromString(String input) {
        try {
            String stringNumber = "";

            // Get everything after the $ character if it is a number
            boolean isNumberScanned = false;
            for (int j = 0; j < input.length(); j++) {
                if (Character.isDigit(input.charAt(j))) {
                    char aChar = input.charAt(j);
                    stringNumber = stringNumber + "" + aChar;
                    isNumberScanned = true;
                }
                else if (isNumberScanned) {
                    break;
                }
            }

            logger.debug("stringNumber=|"+stringNumber+"|");
            
            if (stringNumber==null || stringNumber.trim().length()==0){
                return 0;
            } else {
                // Transform the String to an int value and return it
                try{
                    return new Integer(stringNumber).intValue();
                } catch (NumberFormatException e){
                    logger.fatal("String number is too big for int 32 bit. This is not reasonable because nothing in Craigslist is4 billions worth!");
                    return 1;
                }
            }
        } catch (Exception e) {
            logger.fatal(e);
            e.printStackTrace();
            return 1;
        }
    }
    
    /**
     * Extract the locations from the web page.
     * 
     * @param input
     * @return
     */
    private Collection<String> getLocationsFromString(String input) {
        try {
            logger.debug("getLocationsFromString 11111111111111111111111111111111111111111");
            logger.debug("a) input=|"+input+"|");
            
            Collection<String> aCollLoc=new ArrayList<String>();
            String aWorkString=null;
            if(input == null) return aCollLoc;

            completeLoop: // Break out mark

            // Search for the first '(' character. 
            // Beginning from the right side
            for (int i = input.length()-1; i >= 0 ; i--) {
                if (input.charAt(i) == '(') {
                    // Get everything after the ( character
                    aWorkString=input.substring(i+1);
                    
                    // Cut off the rest including and after the ')' sign
                    for (int j=0;j<aWorkString.length();j++){
                        if (aWorkString.charAt(j) == ')') {
                            aWorkString=aWorkString.substring(0, j);                            
                        }
                    }
    
                    logger.debug("aWorkString=|"+aWorkString+"|");
                    
                    // if there are more locations then one?
                    if (aWorkString.contains("/")==true){
                        Collection<String> aStringCollLocations=new ArrayList<String>();
                        aCollLoc.addAll(this.getLocationsFromCleanStringRecursion(aWorkString, aStringCollLocations, 10));
                        break completeLoop;
                    } else {
                        aCollLoc.add(aWorkString);
                        break completeLoop;
                    }
                }
            }

            logger.debug("ENNNNNDDDD) aCollLoc=|"+aCollLoc.toString()+"|");
            return aCollLoc;
        } catch (Exception e) {
            logger.fatal(e.toString());
            e.printStackTrace();
            return new ArrayList<String>();
        }
    }   
    
    /**
     * Split up the writing style "(dublin / pleasanton / livermore)"
     * @param input
     * @return
     */
    private Collection<String> getLocationsFromCleanStringRecursion(String inputCleanString, Collection<String> inputCollection, int inMaxRecursion) {
        try {   
            logger.debug("getLocationsFromCleanStringRecursion inMaxRecursion="+inMaxRecursion+" +++++++++++++++++++++++++++++++++");
            // input string look like that = Locations=dublin / pleasanton / livermore
            logger.debug("1) inputCleanString=|"+inputCleanString+"|");
                        
            if (inputCleanString.contains("/")==true){
                for (int i=0;i<inputCleanString.length();i++){
                    if (inputCleanString.charAt(i) == '/'){
                        String aTempStringNewAddItem=inputCleanString.substring(0, i).trim();
                        String aTempStringNewRecurItem=inputCleanString.substring(i+1).trim();
                        
                        logger.debug("2) aTempStringNewAddItem=|"+aTempStringNewAddItem+"|\n   aTempStringNewRecurItem=|"+aTempStringNewRecurItem+"|");
                        
                        // Break out condition
                        if (inMaxRecursion<=0){
                            logger.debug("3) MAX MAAAAXXXXRECURSION REACHED inputCleanString=|"+inputCleanString+"|");
                            inputCollection.add(inputCleanString);
                            logger.debug("30) inputCollection=|"+inputCollection.toString()+"|");
                            return inputCollection;
                        } else {
                            logger.debug("35) ELSEEEEE aTempStringNewAddItem=   |"+aTempStringNewAddItem+"|");
                            inputCollection.add(aTempStringNewAddItem);
                            logger.debug("38) inputCollection=|"+inputCollection.toString()+"|");
                            this.getLocationsFromCleanStringRecursion(aTempStringNewRecurItem, inputCollection, (inMaxRecursion-1));
                            return inputCollection;
                        }
                    }
                }
            } else {
                inputCollection.add(inputCleanString.trim());
            }
        
            logger.debug("40) inputCollection=|"+inputCollection.toString()+"|");
            return inputCollection;
        } catch (Exception e) {
            logger.fatal(e);
            inputCollection.add(inputCleanString);
            e.printStackTrace();
            return inputCollection;
        }
    }

    /**
     * GETTER/SETTER.
     * @return
     */
    public String getMatchPattern() {
        return matchPattern;
    }
}