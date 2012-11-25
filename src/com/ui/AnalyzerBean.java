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
public class AnalyzerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(AnalyzerBean.class);
    private Collection<CrawlResultPackage> analyzerOffers;
    private Collection<CrawlResultPackage> crawlResult;
    private transient AnaCrack analyzer;

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
     * This is the main function it is generating the output collection.
     */
    public String getDoAnalyze() {
        logger.info(this.toString());
        // Create the crawler object
        Crawler aCrawl = Crawler.getInstance();
        String craigslistURL = aCrawl.createUrl(
                this.getCategoryCode(),
                this.getLocationURL(),
                this.getKeyword());
        // Get all items
        crawlResult = aCrawl.crawlWebPages(craigslistURL, 100);
        logger.debug("aResultColl Size=" + crawlResult.size());
        // Create analyzer object
        analyzer = new AnaCrack();
        // Do the analyzing and return the offers
        analyzerOffers = analyzer.analyse(
                crawlResult, /* The result collection from the crawler */
                10, /* Give me the x best offers */
                CraigslistAlgorithmEnum.BEST, /* To use algorithm */
                1, /* Lower control limit */
                1000 /* higher control limit */
        );
        return "Analyze Result";
    }

    /**
     * Total Search Result
     * @return
     */
    public String getTotalItems() {
        return (crawlResult != null) ? String.valueOf(crawlResult.size()) : "N/A";
    }

    /**
     * Average Price
     * @return
     */
    public String getAverage() {
        return (analyzer != null) ? String.valueOf(analyzer.getAverage()) : "N/A";
    }

    /**
     * Max Price
     * @return
     */
    public String getMax() {
        return (analyzer != null) ? String.valueOf(analyzer.getMax()) : "N/A";
    }

    /**
     * Min Price
     * @return
     */
    public String getMin() {
        return (analyzer != null) ? String.valueOf(analyzer.getMin()) : "N/A";
    }

    /**
     * Median Price
     * @return
     */
    public String getMedian() {
        // Rounded as integer
        double median = analyzer.getMedian();
        return (analyzer != null) ? String.valueOf(Math.round(median)) : "N/A";
    }

    /**
     * Standard Deviation Price
     * @return
     */
    public String getStandardDeviation() {
        // Rounded to 2 decimal place
        double stddev = analyzer.getStandardDeviation() * 100;
        stddev = Math.round(stddev);
        stddev = stddev / 100;
        return (analyzer != null) ? String.valueOf(stddev) : "N/A";
    }

    /**
     * 10 Best-Price Offers
     * @return
     */
    public Collection<CrawlResultPackage> getBestOffers() {
        return analyzerOffers;
    }
}
