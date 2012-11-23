package com.ui;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.analysis.control.AnaCrack;
import com.crawl.control.Crawler;
import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CraigslistAreasEnum;
import com.crawl.model.CraigslistCategoryEnum;
import com.crawl.model.CrawlResultPackage;

@ManagedBean
@SessionScoped
public class SearchCriteriaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(SearchCriteriaBean.class);

    private String location = "Select a location ......";
    private String category = "Select a category .....";
    private String locationURL;
    private String categoryURL;
    private String keyword;
    private String craigslistUrl;

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.toString();
    }

    public String getLocationURL() {
        return this.locationURL;
    }

    public void setLocationURL(String locationURL) {
        this.locationURL = locationURL;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryURL() {
        return this.categoryURL;
    }

    public void setCategoryURL(String categoryURL) {
        this.categoryURL = categoryURL;
    }

    public String getKeyword() {
        return this.keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String someActionControllerMethod() {
        return "some-page";
    }

    @Override
    public String toString() {
        StringBuilder aRetString = new StringBuilder();

        aRetString.append(location + "\n");
        aRetString.append(category + "\n");
        aRetString.append(locationURL + "\n");
        aRetString.append(categoryURL + "\n");
        aRetString.append(keyword + "\n");

        return aRetString.toString();
    }

    /**
     * This is the main function it is generating the output collection.
     * 
     * @return
     */
    public Collection<CrawlResultPackage> getBestOffers() {
        logger.info(this.toString());
        
        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();

        CraigslistCategoryEnum aCraigslistCategoryEnum=CraigslistCategoryEnum.getCategory(this.getCategoryURL());
        
        
        logger.info("aCraigslistCategoryEnum="+aCraigslistCategoryEnum);
        
        if (aCraigslistCategoryEnum==null){
            logger.error("aCraigslistCategoryEnum IS NULL");
            return null;
        }
        
        CraigslistAreasEnum aCraigslistAreasEnum=CraigslistAreasEnum.getArea(this.getLocationURL());
        
        logger.info("aCraigslistAreasEnum="+aCraigslistAreasEnum);
        
        if (aCraigslistAreasEnum==null){
            logger.error("aCraigslistAreasEnum IS NULL");
            return null;
        }
                
        this.setCraigslistUrl(aCrawl.createUrl(
                aCraigslistCategoryEnum,
                aCraigslistAreasEnum, 
                this.getKeyword()));

        // 2. Step get all offers        
        Collection<CrawlResultPackage> aCrawlResultColl=aCrawl.crawlWebPages(this.getCraigslistUrl(), 100);

        logger.debug("aResultColl Size=" + aCrawlResultColl.size());

        // 3. Create analyzer object
        AnaCrack aAnaCrack = new AnaCrack();

        // 4. do the anlysing and return the offers
        Collection<CrawlResultPackage> aAnaColl = aAnaCrack.analyse(
                aCrawlResultColl, /* The result collection from the crawler */
                10, /* Give me the x best offers */
                CraigslistAlgorithmEnum.BEST, /* To use algorithm */
                1, /* Lower control limit */
                1000 /* higher control limit */
        );

        return aAnaColl;
    }

    public String getCraigslistUrl() {
        return craigslistUrl;
    }

    public void setCraigslistUrl(String craigslistUrl) {
        this.craigslistUrl = craigslistUrl;
    }
}
