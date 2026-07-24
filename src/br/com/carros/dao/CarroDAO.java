package br.com.carros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.carros.exception.BancoDeDadosException;
import br.com.carros.model.Carro;

public class CarroDAO extends BaseDAO {

	public Optional<Carro> findById(long id) {
		String sql = "SELECT * " + " FROM carro " + " WHERE id = ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {

					Carro carro = mapToCarro(rs);
					return Optional.of(carro);
				}
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar carro pelo id: " + id, e);
		}
		return Optional.empty();
	}

	public List<Carro> findByName(String name) {
		if (name == null || name.trim().isEmpty()) {
			return new ArrayList<>();
		}
		List<Carro> carros = new ArrayList<>();
		String sql = "SELECT * " + 
					"FROM carro " + 
					"WHERE LOWER(nome) LIKE ? " + 
					"ORDER BY nome";

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Carro carro = mapToCarro(rs);
					carros.add(carro);
				}
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar carro pelo nome: " + name, e);
		}
		return carros;
	}

	public List<Carro> findAll() {
		List<Carro> carros = new ArrayList<>();
		String sql = "SELECT * FROM carro ORDER BY id";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Carro carro = mapToCarro(rs);
					carros.add(carro);
				}
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar todos os carros. ", e);
		}
		return carros;
	}
	
	public List<Carro> findByTipo(String tipo){
		if (tipo == null || tipo.trim().isEmpty()) {
			return new ArrayList<>();
		}
		List<Carro> carros = new ArrayList<>();
		String sql = "SELECT * " +
	             "FROM carro " +
	             "WHERE LOWER(tipo) LIKE ?" +
	             "ORDER BY tipo";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + tipo.toLowerCase() + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Carro carro = mapToCarro(rs);
					carros.add(carro);
				}
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar carro pelo tipo: ", e);
		}
		return carros;
	}

	private Carro mapToCarro(ResultSet rs) throws SQLException {
		Carro carro = new Carro();
		carro.setId(rs.getLong("id"));
		carro.setTipo(rs.getString("tipo"));
		carro.setNome(rs.getString("nome"));
		carro.setDescricao(rs.getString("descricao"));
		carro.setUrlFoto(rs.getString("url_foto"));
		carro.setUrlVideo(rs.getString("url_video"));
		carro.setLatitude(rs.getDouble("latitude"));
		carro.setLongitude(rs.getDouble("longitude"));
		return carro;
	}

}
