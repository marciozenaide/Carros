package br.com.carros.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDAO {
	
	private static final String URL = "jdbc:mysql://localhost:3306/carros";
	private static final String USUARIO = "livro";
	private static final String SENHA = "livro123";

	protected Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, USUARIO, SENHA);
	}

}
