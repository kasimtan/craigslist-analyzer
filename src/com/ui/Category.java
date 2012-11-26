package com.ui;

/**
 * Category data model object
 * @author Team Kappa
 *
 */
public class Category {
    private String name;
    private String code;

    /**
     * Constructor.
     * @param name
     * @param code
     */
    public Category(String name, String code) {
        this.name = name;
        this.code = code;
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
    public String getCode() {
        return code;
    }
}
