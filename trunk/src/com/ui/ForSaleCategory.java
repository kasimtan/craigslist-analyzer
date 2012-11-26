package com.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * JSF data object model for the ForSale category
 * @author Team Kappa
 *
 */
@ManagedBean(eager=true)
public class ForSaleCategory {
    private static List<Category> categories;

    /**
     * Constructor.
     */
    public ForSaleCategory() {
        String url = "http://sfbay.craigslist.org/sss/";
        try {
            Document document = Jsoup.connect(url).get();
            Elements options = document.select("select#catAbb option");
            categories = new ArrayList<Category>();
            for(Element option : options) {
                String name = option.text();
                String code = option.val();
                // Exclude empty category
                if("".equals(code)) continue;
                // Exclude categories start with "all" except "all for sale"
                if(name.startsWith("all") && !name.startsWith("all for sale")) continue;
                // Rename all for sale
                if(name.startsWith("all for sale"))
                    name = "all for sale";
                Category category = new Category(capitalFirst(name), code);
                categories.add(category);
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * GETTER/SETTER.
     * @return
     */
    public List<Category> getCategories() {
        return categories;
    }
    
    /**
     * Put the capital first.
     * @param str
     * @return
     */
    private static String capitalFirst(String str) {
        // Capitalize first letter of the string
        StringBuilder capitalWordBuilder = new StringBuilder(str.substring(0,1).toUpperCase());
        capitalWordBuilder.append(str.substring(1, str.length()));
        return capitalWordBuilder.toString();
    }
}
