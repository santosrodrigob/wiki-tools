package br.com.tools.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class GetProperties {
	
	public Properties getProperties() throws Exception {
		
	    Properties properties = new Properties();
	    FileInputStream file = new FileInputStream( "jdbc.properties"); 
	    properties.load(file);
	    return properties; 
	}

	public static void main(String args[]) throws Exception { 

//		String login;
//		String password;
//		String driver;
//		String url;
//		System.out.println("************Lendo o arquivo de propriedades************");
//
//	   Properties properties = getProperties();
//	   login = properties.getProperty("jdbc.wiki.tools.username");
//	   password = properties.getProperty("jdbc.wiki.tools.password");
//	   driver = properties.getProperty("jdbc.wiki.tools.driverClassName");
//	   url = properties.getProperty("jdbc.wiki.tools.local.url");
//
//	   System.out.println(login);
//	   System.out.println(password);
//	   System.out.println(driver);
//	   System.out.println(url);
	}
}
