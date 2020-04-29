/**
 * @author Rodrigo Borges 
 * @date 26/04/2020
 */

package br.com.tools.configuracao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import br.com.tools.utils.ResourceProperties;

@Configuration
@PropertySource("classpath:jdbc.properties")
public class ConnectionFactory {
	
	@Autowired
	private Environment env;

	@Bean
	public DataSource getDataSource() {
		  
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
	    dataSource.setUrl(env.getProperty("jdbc.url"));
	    dataSource.setUsername(env.getProperty("jdbc.username"));
	    dataSource.setPassword(env.getProperty("jdbc.password"));
	    return dataSource;
	}
	
	private static String JDBC_DRIVER = "jdbc.driverClassName";	
	private static String JDBC_URL = "jdbc.url";
	private static String JDBC_USERNAME = "jdbc.username";
	private static String JDBC_PASSWORD = "jdbc.password";

    private static Connection openConnection()
    {
    	Connection connection = null;
    	    	
        try {
            Class.forName(getDataBaseDriver());
            final String url = getDataBaseURL();

            final Properties properties = new Properties();
            properties.put("user", getDataBaseUser());
            properties.put("password", getDataBaseUserPassword());
            connection = DriverManager.getConnection(url, properties);     
        } catch (SQLException e) {
        	e.printStackTrace();
        	System.out.println("Erro SQL=> " + e);
        } catch (ClassNotFoundException e) {
        	e.printStackTrace();
        	System.out.println("Erro Class=> " + e);
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("Erro de conexão com banco de dados =>"+e.getMessage());
        }
        return connection;
    }

    public static Connection getConnection() throws Exception{
    		return openConnection();
    }
    
    /**
     * Get database driver.
     * 
     * @return database driver.
     */
    private static String getDataBaseDriver(){
    	return ResourceProperties.getPropertyWEB(JDBC_DRIVER);
    }
    
    /**
     * Get database path.
     * 
     * @return database path.
     */
    private static String getDataBaseURL(){
    	return ResourceProperties.getPropertyWEB(JDBC_URL);
    }

    /**
     * Get database user.
     * 
     * @return database user.
     */
    private static String getDataBaseUser() {
        return ResourceProperties.getPropertyWEB(JDBC_USERNAME);
    }

    /**
     * Get database password.
     * 
     * @return database password.
     */
    private static String getDataBaseUserPassword() {
        return ResourceProperties.getPropertyWEB(JDBC_PASSWORD);
    }

	public static void closeConnection(final Connection connection) {
        if (connection != null) {
            try {
            	connection.close();
            } catch (SQLException e) {
            	e.printStackTrace();
            	System.out.println(e.getMessage());
            }
        }
    }
	
}
