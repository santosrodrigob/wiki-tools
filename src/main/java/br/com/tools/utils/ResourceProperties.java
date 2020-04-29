package br.com.tools.utils;

import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 *
 * @author Andrew Betencourt
 */
public class ResourceProperties {

    public static final int LOG4J_PROPERTIES = 1;
    
    private static ResourceProperties instance;
    private Properties log4jProperties;
    
    private ResourceProperties() throws Exception {
        this.log4jProperties = new Properties();
        this.log4jProperties.load(getClass().getClassLoader().getResourceAsStream("log4j.properties"));
    }
    
    public static ResourceProperties getInstance() throws Exception{
        if(instance == null)
            instance = new ResourceProperties();
        return instance;
    }
    
    public String getProperty(int type, String key) throws Exception {
        if(getProperties(type).containsKey(key))
            return getProperties(type).getProperty(key);
        throw new Exception("Propertie not found!");
    }
    
    public Properties getProperties(int type) 
    {
        switch(type) {
            case 1: return this.log4jProperties;
        }
        throw new NullPointerException("Invalid ResourceType " + type);
    }  
    
    public static String getPropertyWEB(String propriedade) {
        PropertyResourceBundle props = 
            (PropertyResourceBundle)ResourceBundle.getBundle("jdbc");
        return props.getString(propriedade);
    }

    public static String getPropertyWEB(String propriedade, String arg) {
        PropertyResourceBundle props = 
            (PropertyResourceBundle)ResourceBundle.getBundle("jdbc");
        return props.getString(propriedade).replaceAll("\\{0\\}", arg);
    }
       
//    public static Properties getProperties(){
//		  final Properties prop = new Properties();		  
//	    	try {
//	            //load a properties file from class path, inside static method
//	    		prop.load(ServletUtils.class.getClassLoader().getResourceAsStream("config.properties"));
//	    	} catch (final IOException ex) {
//	    		ex.printStackTrace();
//	        }
//	    	return prop;
//	}
//    
//    public static String getPropertieConfig(final String propriedade){
//	    Object propertieValue = "";
//    	try {
//    		final Properties prop = new Properties();
//            //load a properties file from class path, inside static method
//    		prop.load(ServletUtils.class.getClassLoader().getResourceAsStream("config.properties"));
//    		propertieValue = prop.get(propriedade);
//    	} catch (final IOException ex) {
//    		ex.printStackTrace();
//        }
//    	return propertieValue.toString();
//	}
    
    public static String getPropertyConfig(final String propriedade) 
    {
    	final PropertyResourceBundle props =  (PropertyResourceBundle)ResourceBundle.getBundle("config");
        return props.getString(propriedade);
    }
}