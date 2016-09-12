package com.veridu.idos.utils;

import java.util.HashMap;

public class Filter {

    private HashMap<String, String> params = new HashMap<>();

    private Filter() {
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public Filter addSourceNameFilter(String filter) {
        return this.addFilterByKeyName("source.name", filter);
    }

    /**
     * Use this filter to get profile features not related
     * to a particular source (e.g., self submitted).
     * 
     * @return
     */
    public Filter addSourceNullFilter() {
        return this.addFilterByKeyName("source.id", "0");
    }

    public Filter addNameFilter(String filter) {
        return this.addFilterByKeyName("name", filter);
    }

    public Filter addCreatorFilter(String filter) {
        this.addFilterByKeyName("creator", filter);
        return this;
    }

    public Filter addTypeFilter(String filter) {
        this.addFilterByKeyName("type", filter);
        return this;
    }

    /**
     * Add a filter passing the name of the filter parameter.
     * 
     * @param name parameter on which to run the filter
     * @param filter actual filter value
     * 
     * @return
     */
    public Filter addFilterByKeyName(String name, String filter) {
        this.params.put(name, filter);
        return this;
    }

    public static Filter createFilter() {
        return new Filter();
    }

    @Override
    public String toString() {
        int size = this.params.keySet().size();

        String[] queryElements = new String[size];

        int i = size - 1;
        for (String key : this.params.keySet())
            queryElements[i--] = key + "=" + this.params.get(key);

        return String.join("&", queryElements);
    }

}
