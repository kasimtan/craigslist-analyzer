package com.ui;

import java.util.*;

public class Country {
    private String name;
    private String link;
    private List<State> states; 

    public Country(String name, String link, List<State> states) {
        this.name = name;
        this.link = link;
        this.states = states;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public List<State> getStates() {
        return states;
    }
}