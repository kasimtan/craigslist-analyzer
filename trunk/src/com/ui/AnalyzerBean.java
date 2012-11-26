package com.ui;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Collection;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.analysis.control.AnaCrack;
import com.crawl.control.Crawler;
import com.crawl.model.CraigslistAlgorithmEnum;
import com.crawl.model.CrawlResultPackage;
import com.crawl.model.LocationDistribution;
import com.crawl.model.PriceDistribution;

/**
 * Main JSF bean for the analyzing and presentation of the results.
 * @author Team Kappa
 *
 */
@ManagedBean
@SessionScoped
public class AnalyzerBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Logger logger = Logger.getLogger(AnalyzerBean.class);
    private transient Collection<CrawlResultPackage> analyzerOffers;
    private transient Collection<CrawlResultPackage> crawlResult;
    private transient Collection<LocationDistribution> locationDistribution;
    private transient Collection<PriceDistribution> priceDistribution;
    private transient AnaCrack analyzer;

    private String location = "Select a location ......";
    private String category = "Select a category .....";
    private String locationURL;
    private String categoryCode;
    private String keyword;

    /**
     * GETTER/SETTER.
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     * GETTER/SETTER.
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * GETTER/SETTER.
     * @return
     */
    public String getLocationURL() {
        return locationURL;
    }

    /**
     * GETTER/SETTER.
     * @param locationURL
     */
    public void setLocationURL(String locationURL) {
        this.locationURL = locationURL;
    }

    /**
     * GETTER/SETTER.
     * @return
     */
    public String getCategory() {
        return category;
    }

    /**
     * GETTER/SETTER.
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * GETTER/SETTER.
     * @return
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * GETTER/SETTER.
     * @param categoryCode
     */
    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    /**
     * GETTER/SETTER.
     * @return
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * GETTER/SETTER.
     * @param keyword
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    /**
     * toString method.
     */
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
        crawlResult = aCrawl.crawlWebPages(craigslistURL, 2500);
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
        if(analyzer == null)
            return "N/A";
        // Rounded to 2 decimal place
        double average = analyzer.getAverage() * 100.0;
        average = Math.round(average);
        average = average / 100;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(average);
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
        if(analyzer == null)
            return "N/A";
        // Rounded as integer
        double median = analyzer.getMedian();
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(Math.round(median));
    }

    /**
     * Standard Deviation Price
     * @return
     */
    public String getStandardDeviation() {
        if(analyzer == null)
            return "N/A";
        // Rounded to 2 decimal place
        double stddev = analyzer.getStandardDeviation() * 100.0;
        stddev = Math.round(stddev);
        stddev = stddev / 100;
        NumberFormat nf = NumberFormat.getInstance();
        nf.setGroupingUsed(false);
        return nf.format(stddev);
    }

    /**
     * 10 Best-Price Offers
     * @return
     */
    public Collection<CrawlResultPackage> getBestOffers() {
        return analyzerOffers;
    }

    /**
     * Check 10 Best-Price Offers isEmpty
     * @return
     */
    public boolean isBestOffersEmpty() {
        return (analyzerOffers == null) ? true : analyzerOffers.isEmpty();
    }
    
    public String getPriceDistribution() {
        StringBuilder str = new StringBuilder();
        String[] colors = {"#FF0F00", "#FF6600", "#FF9E01", "#FCD202", "#B0DE09", "#04D215", "#0D8ECF", "#0D52D1", "#2A0CD0", "#8A0CCF", "#CD0D74", "#754DEB", "#999999", "#666666", "#333333"};
        if(analyzer != null) {
            priceDistribution = analyzer.getPriceDistribution();
            int i = 0;
            for(PriceDistribution price : priceDistribution) {
                if(i > 0) str.append(", ");
                str.append("{ price:'" + price.getPriceString() + "', count:" + price.getCount() + ", color:'" + colors[i % 15] + "' }");
                i++;
            }
        }
        return str.toString();
    }
    
    /**
     * Get the location distribution.
     * @return
     */
    public String getLocationDistribution() {
        StringBuilder str = new StringBuilder();
        if(analyzer != null) {
            locationDistribution = analyzer.getLocationDistribution();
            int i = 0;
            for(LocationDistribution loc : locationDistribution) {
                if(i > 0) str.append(", ");
                str.append("{ area:'" + loc.getAreaName() + "', count:" + loc.getCount() + " }");
                i++;
            }
        }
        return str.toString();
    }
}