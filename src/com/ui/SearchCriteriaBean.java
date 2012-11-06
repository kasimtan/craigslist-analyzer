package com.ui;

import javax.faces.bean.*;
import org.apache.log4j.Logger;

import java.io.Serializable;

@ManagedBean
public class SearchCriteriaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    static Logger logger = Logger.getLogger(SearchCriteriaBean.class);

    private String location = "Select a location ......";
    private String category = "Select a category .....";
    private String locationURL, categoryURL, keyword;

    public String getLocation() {
        return(location);
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationURL() {
        return(locationURL);
    }

    public void setLocationURL(String locationURL) {
        this.locationURL = locationURL;
    }

    public String getCategory() {
        return(category);
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryURL() {
        return(categoryURL);
    }

    public void setCategoryURL(String categoryURL) {
        this.categoryURL = categoryURL;
    }

    public String getKeyword() {
        return(keyword);
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String someActionControllerMethod() {
        return("some-page");
    }
}
