package com.veridu.sdk_java;

import com.veridu.sdk_java.endpoints.Companies;
import com.veridu.sdk_java.exceptions.EmptyPrivateKey;

/**
 * Factory Endpoint creates all Endpoints
 *
 */
public class Factory {
	
	public static String privateKey;
	
	/**
	 * Companies instance
	 */
	public Companies company;
	
	
	public Factory (String privateKey) throws EmptyPrivateKey {
		if (privateKey.isEmpty() || privateKey == null) {
			throw new EmptyPrivateKey();
		} 
		
		Factory.privateKey = privateKey;
	}
	
	public Factory () throws EmptyPrivateKey {
		if (Factory.privateKey == null) {
			throw new EmptyPrivateKey();
		}
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
