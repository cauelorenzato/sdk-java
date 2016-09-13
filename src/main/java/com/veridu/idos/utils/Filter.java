package com.veridu.idos.utils;

import java.util.HashMap;

public class Filter {

    protected HashMap<String, String> params = new HashMap<>();
    /**
     * Boolean value to get all pages
     */
    private boolean getAllPages = false;

    /**
     * Constructor Class
     */
    private Filter() {
    }

    /**
     * Return all parameters added in the HashMap<String, String>
     * 
     * @return
     */
    public HashMap<String, String> getParams() {
        return params;
    }

    /**
     * Adds a source name filter
     * 
     * @param filter
     * @return
     */
    public Filter addSourceNameFilter(String filter) {
        return this.addFilterByKeyName("source:name", filter);
    }

    /**
     * Use this filter to get profile features not related to a particular
     * source (e.g., self submitted).
     * 
     * @return self
     */
    public Filter addSourceNullFilter() {
        return this.addFilterByKeyName("source:id", "0");
    }

    /**
     * Adds a name filter
     * 
     * @param filter
     * @return
     */
    public Filter addNameFilter(String filter) {
        return this.addFilterByKeyName("name", filter);
    }

    /**
     * Adds a creator filter
     * 
     * @param filter
     * @return
     */
    public Filter addCreatorFilter(String filter) {
        this.addFilterByKeyName("creator", filter);
        return this;
    }

    /**
     * Adds a type filter
     * 
     * @param filter
     * @return
     */
    public Filter addTypeFilter(String filter) {
        this.addFilterByKeyName("type", filter);
        return this;
    }

    /**
     * Add a filter passing the name of the filter parameter.
     * 
     * @param name
     *            parameter on which to run the filter
     * @param filter
     *            actual filter value
     * 
     * @return self
     */
    public Filter addFilterByKeyName(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    /**
     * Sets the pagination number and adds it to the filter
     * 
     * @param page
     * @return
     */
    public Filter setPaginationNumber(int page) {
        this.params.put("page", String.valueOf(page));
        return this;
    }

    /**
     * Sets the limite per page and adds it to the filter
     * 
     * @param limitPerPage
     * @return
     */
    public Filter setLimitPerPage(int limitPerPage) {
        this.params.put("perPage", String.valueOf(limitPerPage));
        return this;
    }

    /**
     * Factory to create a new Filter object
     * 
     * @return
     */
    public static Filter createFilter() {
        return new Filter();
    }

    /**
     * Overrides the toString() method
     */
    @Override
    public String toString() {
        int size = this.params.keySet().size();

        String[] queryElements = new String[size];

        int i = size - 1;
        for (String key : this.params.keySet())
            queryElements[i--] = key + "=" + this.params.get(key);

        return String.join("&", queryElements);
    }

    /**
     * Sets the getAllPages property to true
     */
    public void setAllPagesTrue() {
        this.getAllPages = true;
    }

    /**
     * Return the boolean value of the property getAllPages
     * 
     * @return
     */
    public boolean getAllPagesTrue() {
        return this.getAllPages;
    }
}
