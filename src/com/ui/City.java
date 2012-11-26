package com.ui;

/**
 * City data object model
 * @author Team Kappa
 *
 */
public class City {
    private String name;
    private String link;

    /**
     * Constructor
     * @param name
     * @param link
     */
    public City(String name, String link) {
        this.name = name;
        this.link = link;
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
}
