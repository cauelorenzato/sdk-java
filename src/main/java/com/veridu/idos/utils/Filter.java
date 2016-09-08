package com.veridu.idos.utils;

import java.util.HashMap;

public class Filter {

    private HashMap<String, String> params = new HashMap<>();

    private Filter() {
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public Filter addNameFilter(String filter) {
        return this.addFilterByKeyName("name", filter);
    }

    public Filter addProviderFilter(String filter) {
        this.addFilterByKeyName("provider", filter);
        return this;
    }

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
