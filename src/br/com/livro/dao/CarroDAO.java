package br.com.livro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.livro.exception.BancoDeDadosException;
import br.com.livro.model.Carro;

public class CarroDAO extends BaseDAO {

	public Optional<Carro> getCarroById(long id) {
		String sql = "select * from carro where id=?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {

					Carro carro = createCarro(rs);
					return Optional.of(carro);
				}
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar carro pelo id: " + id, e);
		}
		return Optional.empty();
	}

	public List<Carro> findByName(String name) {
		List<Carro> carros = new ArrayList<>();
		String sql = "SELECT * " +
	             "FROM carro " +
	             "WHERE LOWER(nome) LIKE ?";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, "%" + name.toLowerCase() + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					Carro carro = createCarro(rs);
					carros.add(carro);
				}
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar carro pelo nome: " + name, e);
		}
		return carros;
	}

	private Carro createCarro(ResultSet rs) throws SQLException {
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
