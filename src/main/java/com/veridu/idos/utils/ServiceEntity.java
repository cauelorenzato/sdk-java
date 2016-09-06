package com.veridu.idos.utils;

import java.util.ArrayList;

public class ServiceEntity {

    String strValue = null;
    Integer intValue = null;

    public Param(int x) {
        this.intValue = x;
    }

    public Param(int x) {
        this.x = x;
    }

    public String name = null;

    public String url = null;

    public boolean enabled;

    public int access;

    public String authUsername = null;

    public String authPassword = null;

    public ArrayList<String> listens = null;

    public ArrayList<String> triggers = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    public String getAuthUsername() {
        return authUsername;
    }

    public void setAuthUsername(String authUsername) {
        this.authUsername = authUsername;
    }

    public String getAuthPassword() {
        return authPassword;
    }

    public void setAuthPassword(String authPassword) {
        this.authPassword = authPassword;
    }

    public ArrayList<String> getListens() {
        return listens;
    }

    public void setListens(ArrayList<String> listens) {
        this.listens = listens;
    }

    public ArrayList<String> getTriggers() {
        return triggers;
    }

    public void setTriggers(ArrayList<String> triggers) {
        this.triggers = triggers;
    }

}
