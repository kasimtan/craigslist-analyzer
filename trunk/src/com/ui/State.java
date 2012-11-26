package com.ui;

import java.util.List;

/**
 * State object model
 * @author Team Kappa
 *
 */
public class State {
    private String name;
    private String link;
    private List<City> cities;

    /**
     * Constructor
     * @param name
     * @param link
     * @param cities
     */
    public State(String name, String link, List<City> cities) {
        this.name = name;
        this.link = link;
        this.cities = cities;
    }

    /**
     * GETTER/SETTER.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * GETTER/SETTER.
     * @return
     */
    public String getLink() {
        return link;
    }

    /**\
     * GETTER/SETTER.
     * @return
     */
    public List<City> getCities() {
        return cities;
    }
}