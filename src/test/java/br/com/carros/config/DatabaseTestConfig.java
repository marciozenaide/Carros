package br.com.carros.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.carros.util.ConnectionFactory;

public final class DatabaseTestConfig {
	
	 private DatabaseTestConfig() {
		 
	 }
	
	public static void startH2() throws SQLException, IOException {
		//Lê schema.sql
	    try (Connection connection = ConnectionFactory.getConnection();
	         InputStream input = DatabaseTestConfig.class.getClassLoader().getResourceAsStream("schema.sql")) {

	        if (input == null) {
	            throw new IllegalStateException("schema.sql não encontrado nos recursos de teste.");
	        }
	        
	        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        		Statement stmt = connection.createStatement()) {
	        	
	        	StringBuilder sql = new StringBuilder();
	        	String linha;
	        	while ((linha = reader.readLine()) != null) {
	        		sql.append(linha).append("\n");
	        	}
	        	
	        	// Executa o script de criação completo
	        	stmt.execute(sql.toString());
	        }
	    }
	}
}
