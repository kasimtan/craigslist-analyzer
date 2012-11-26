package com.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.bean.ManagedBean;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * JSF world data object model.
 * @author Team Kappa.
 *
 */
@ManagedBean(eager=true)
public class World {
    private List<Country> countryList;

    /**
     * Constructor.
     */
    public World() {
        String url = "http://www.craigslist.org/about/sites";
        try {
            Document document = Jsoup.connect(url).get();
            Elements countries = document.select("div.colmask");
            countryList = new ArrayList<Country>();
            for(Element country : countries) {
                String countryLink = "#";
                String countryName = country.select("h1.continent_header").text();
                // Rename US to United States
                if("US".equals(countryName)) countryName = "United States";
                Elements states = country.select("div.state_delimiter");
                List<State> stateList = new ArrayList<State>();
                for(Element state : states) {
                    String stateName = state.text();
                    String stateLink = "#";
                    Elements cities = state.nextElementSibling().select("li");
                    List<City> cityList = new ArrayList<City>();
                    for(Element city : cities) {
                        Elements links = city.getElementsByTag("a");
                        if("".equals(stateName.trim())) {
                            // No state, then set city as the state
                            stateName = capitalWord(city.text());
                            stateLink = links.attr("abs:href");
                        }
                        City cityObj = new City(capitalWord(city.text()), links.attr("abs:href"));
                        cityList.add(cityObj);
                    }
                    State stateObj = new State(stateName, stateLink, cityList);
                    stateList.add(stateObj);
                }
                Country countryObj = new Country(countryName, countryLink, stateList);
                countryList.add(countryObj);
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
    public List<Country> getCountries() {
        return countryList;
    }
    
    /**
     * Put capital first.
     * @param str
     * @return
     */
    private static String capitalWord(String str) {
        // Capitalize letter after space, bracket, slash or dash character
        Pattern spaces = Pattern.compile("(\\s+[a-z])|(\\(+[a-z])|(\\-+[a-z])|(\\/+[a-z])");
        Matcher m = spaces.matcher(str);
        StringBuilder capitalWordBuilder = new StringBuilder(str.substring(0,1).toUpperCase());
        int prevStart = 1;
        while(m.find()) {
            capitalWordBuilder.append(str.substring(prevStart, m.end()-1));
            capitalWordBuilder.append(str.substring(m.end()-1, m.end()).toUpperCase());
            prevStart = m.end();
        }
        capitalWordBuilder.append(str.substring(prevStart, str.length()));
        return capitalWordBuilder.toString();
    }
}