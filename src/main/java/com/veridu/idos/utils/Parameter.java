package com.veridu.idos.utils;

import java.util.HashMap;

public class Parameter {

    protected HashMap<String, String> params = new HashMap<>();

    public HashMap<String, String> getParams() {
        return params;
    }

    /**
     * Add a parameter passing the name of the parameter and its value.
     * 
     * @param name
     *            parameter name
     * @param value
     *            parameter value
     * 
     * @return
     */
    public Parameter addParameterByKeyName(String name, String value) {
        this.params.put(name, value);
        return this;
    }

    public Parameter setPaginationNumber(int page) {
        this.params.put("page", String.valueOf(page));
        return this;
    }

    public Parameter setLimitPerPage(int limitPerPage) {
        this.params.put("perPage", String.valueOf(limitPerPage));
        return this;
    }

    public Parameter addGetAllPagesParameter() {
        this.params.put("getAllPages", String.valueOf("true"));
        return this;
    }

    public String toString() {
        int size = this.params.keySet().size();

        String[] queryElements = new String[size];

        int i = size - 1;
        for (String key : this.params.keySet())
            queryElements[i--] = key + "=" + this.params.get(key);

        return String.join("&", queryElements);
    }

}
