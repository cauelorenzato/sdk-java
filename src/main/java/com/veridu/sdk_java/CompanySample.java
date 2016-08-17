package com.veridu.sdk_java;

import java.io.UnsupportedEncodingException;

import com.veridu.sdk_java.endpoints.Companies;
import com.veridu.sdk_java.exceptions.SDKException;

public class CompanySample {

	public static void main(String[] args) throws SDKException, UnsupportedEncodingException {
		String companyPrivKey = "";
		Factory factory = new Factory(companyPrivKey);
		System.out.println(factory.getCompany().listAll());
		System.out.println(factory.getCompany().create("Company Example"));
		System.out.println(factory.getCompany().update("Updated Company", "company-example"));
		System.out.println(factory.getCompany().getOne("updated-company"));
		System.out.println(factory.getCompany().delete("updated-company"));
	}
}
