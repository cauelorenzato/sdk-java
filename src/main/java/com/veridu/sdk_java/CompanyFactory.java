package com.veridu.sdk_java;

import com.veridu.sdk_java.endpoints.Companies;
import com.veridu.sdk_java.exceptions.EmptyPrivateKey;

/**
 * Factory Endpoint creates all Endpoints
 *
 */
public class CompanyFactory {
	
	public static String privateKey;
	
	/**
	 * Companies instance
	 */
	public Companies company;
	
	
	public CompanyFactory (String privateKey) throws EmptyPrivateKey {
		if (privateKey.isEmpty() || privateKey == null) {
			throw new EmptyPrivateKey();
		} 
		
		CompanyFactory.privateKey = privateKey;
	}
	
	/**
	 * Instantiates Companies endpoint
	 * 
	 * @return
	 * 
	 * @throws EmptyPrivateKey
	 */
	public Companies getCompany() throws EmptyPrivateKey {
		if (!(this.company instanceof Companies)) {
			this.company = new Companies();
		}
		return this.company;
	}
	
  
}
