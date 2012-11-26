package com.ui;

import java.util.List;

/**
 * Country data object model.
 * @author Team Kappa
 *
 */
public class Country {
    private String name;
    private String link;
    private List<State> states; 

    /** 
     * Constructor
     * @param name
     * @param link
     * @param states
     */
    public Country(String name, String link, List<State> states) {
        this.name = name;
        this.link = link;
        this.states = states;
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

    /**
     * GETTER/SETTER.
     * @return
     */
    public List<State> getStates() {
        return states;
    }
}