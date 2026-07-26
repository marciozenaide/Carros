package br.com.carros.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public final class ConnectionFactory {

	private static final String URL = "db.url";
	private static final String USER = "db.user";
	private static final String PASSWORD = "db.password";
	private static final String DRIVER = "db.driver";

	private static final Properties PROPERTIES = loadProperties();
	
	private static final Logger LOGGER =
	        LogFactory.getLogger(ConnectionFactory.class);

	private static Properties loadProperties() {

	    Properties properties = new Properties();

	    try (InputStream input =
	            ConnectionFactory.class.getClassLoader()
	                    .getResourceAsStream("db.properties")) {

	        if (input == null) {
	            throw new RuntimeException("Arquivo db.properties não encontrado.");
	        }
	        
	        properties.load(input);

	        Class.forName(properties.getProperty(DRIVER));

	        return properties;

	    } catch (IOException e) {
	        throw new RuntimeException("Erro ao carregar db.properties.", e);
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("Driver JDBC não encontrado: " + properties.getProperty(DRIVER),e);
	    }
	}

	private ConnectionFactory() {
	}

	public static Connection getConnection() throws SQLException {
		
		LOGGER.fine("Abrindo conexão com o banco.");
		
	    return DriverManager.getConnection(
	            PROPERTIES.getProperty(URL),
	            PROPERTIES.getProperty(USER),
	            PROPERTIES.getProperty(PASSWORD));
	}
	
}