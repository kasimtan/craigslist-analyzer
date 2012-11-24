package com.ui;

import java.util.*;

public class State {
    private String name;
    private String link;
    private List<City> cities;

    public State(String name, String link, List<City> cities) {
        this.name = name;
        this.link = link;
        this.cities = cities;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public List<City> getCities() {
        return cities;
    }
}