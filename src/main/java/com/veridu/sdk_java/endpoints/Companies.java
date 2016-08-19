package com.veridu.sdk_java.endpoints;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.veridu.sdk_java.exceptions.EmptyPrivateKey;
import com.veridu.sdk_java.exceptions.SDKException;

/**
 * 
 * @version 2.0
 */
public class Companies extends AbstractEndpoint {

	/**
	 * Class Constructor
	 * 
	 * @throws EmptyPrivateKey 
	 */
	public Companies() throws EmptyPrivateKey {
		super();
	}
	
	/**
	 * Lists all companies available 
	 * 
	 * @return String response
	 * 
	 * @throws SDKException
	 */
	public JsonObject listAll() throws SDKException {
		return this.fetch("GET", "companies");
	}
	
	/**
	 * Retrieves the respective company for the given companySlug 
	 * 
	 * @param String companySlug The company slug to retrieve data for
	 * 
	 * @return String response
	 *
	 * @throws SDKException 
	 */
	public JsonObject getOne(String companySlug) throws SDKException {
		return this.fetch("GET", "companies/"+companySlug);
	}
	
	/**
	 * Creates a new company
	 * 
	 * @param name
	 * 
	 * @return String response
	 * 
	 * @throws SDKException
	 * @throws UnsupportedEncodingException
	 */
	public JsonObject create(String name) throws SDKException, UnsupportedEncodingException {
		String data = this.queryBuilder("name", name);
		return this.fetch("POST", "companies", data);
	}
	
	/**
	 * Updates an existing company given the companySlug
	 * 
	 * @param name
	 * @param companySlug
	 * 
	 * @return String response
	 * 
	 * @throws SDKException
	 * @throws UnsupportedEncodingException
	 */
	public JsonObject update(String name, String companySlug) throws SDKException, UnsupportedEncodingException {
		String data = this.queryBuilder("name", name);
		return this.fetch("PUT", "companies/"+companySlug, data);
	}
	
	/**
	 * Deletes an existing company given the company slug
	 * 
	 * @param companySlug
	 * 
	 * @return String response
	 * 
	 * @throws SDKException
	 */
	public JsonObject delete(String companySlug) throws SDKException {
		return this.fetch("DELETE", "companies/"+companySlug);
	}
}
