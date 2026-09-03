package br.com.carros.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.carros.util.ConnectionFactory;

public abstract class BaseDAO {

    protected Connection getConnection() throws SQLException {
        return ConnectionFactory.getConnection();
    }
}
