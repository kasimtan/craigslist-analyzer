package com.ui;

import java.io.Serializable;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.analysis.control.AnaCrack;
import com.crawl.control.Crawler;
import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CrawlResultPackage;

@ManagedBean
@SessionScoped
public class SearchCriteriaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(SearchCriteriaBean.class);

    private String location = "Select a location ......";
    private String category = "Select a category .....";
    private String locationURL;
    private String categoryCode;
    private String keyword;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationURL() {
        return locationURL;
    }

    public void setLocationURL(String locationURL) {
        this.locationURL = locationURL;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String toString() {
        StringBuilder aRetString = new StringBuilder();
        aRetString.append(location + "\n");
        aRetString.append(category + "\n");
        aRetString.append(locationURL + "\n");
        aRetString.append(categoryCode + "\n");
        aRetString.append(keyword + "\n");
        return aRetString.toString();
    }

    /**
     * 10 Best-Price Offers
     * @return
     */
    public Collection<CrawlResultPackage> getBestOffers() {
        logger.info(this.toString());
        // 1. Create the crawler object
        Crawler aCrawl = Crawler.getInstance();
        logger.info("aCraigslistCategoryEnum=" + categoryCode);
        logger.info("aCraigslistAreasEnum=" + locationURL);
        logger.info("aCraigslistKeyword=" + keyword);
        String craigslistURL = aCrawl.createUrl(
                getCategoryCode(),
                getLocationURL(),
                getKeyword());
        // 2. Step get all offers        
        Collection<CrawlResultPackage> aCrawlResultColl = aCrawl.crawlWebPages(craigslistURL, 100);
        logger.debug("aResultColl Size=" + aCrawlResultColl.size());
        // 3. Create analyzer object
        AnaCrack aAnaCrack = new AnaCrack();
        // 4. Do the analysing and return the offers
        Collection<CrawlResultPackage> aAnaColl = aAnaCrack.analyse(
                aCrawlResultColl, /* The result collection from the crawler */
                10, /* Give me the x best offers */
                CraigslistAlgorithmEnum.BEST, /* To use algorithm */
                1, /* Lower control limit */
                1000 /* higher control limit */
        );
        return aAnaColl;
    }
}
