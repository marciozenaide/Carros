package br.com.carros.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.com.carros.exception.BancoDeDadosException;
import br.com.carros.model.Carro;
import br.com.carros.util.LogFactory;

public class CarroDAO extends BaseDAO {
	
	private static final String SQL_INSERT =
	        "INSERT INTO carro " +
	        "(nome, descricao, url_foto, url_video, latitude, longitude, tipo) " +
	        "VALUES (?, ?, ?, ?, ?, ?, ?)";

	private static final String SQL_UPDATE =
			 "UPDATE carro SET " +
			 "nome = ?, " +
		     "descricao = ?, " +
		     "url_foto = ?, " +
		     "url_video = ?, " +
		     "latitude = ?, " +
		     "longitude = ?, " +
		     "tipo = ? " +
		     "WHERE id = ?";
	
	private static final String SQL_FIND_BY_ID =
	        "SELECT * FROM carro WHERE id = ?";
	
	private static final String SQL_FIND_ALL =
	        "SELECT * FROM carro ORDER BY id";
	
	private static final String SQL_FIND_BY_NAME =  
			"SELECT * FROM carro " +
	        "WHERE LOWER(nome) LIKE ? " +
	        "ORDER BY nome";

	private static final String SQL_FIND_BY_TIPO =  
			"SELECT * FROM carro " +
	        "WHERE LOWER(tipo) = ? " +
	        "ORDER BY tipo";
	
	private static final String SQL_DELETE =
	        "DELETE FROM carro WHERE id = ?";
	
	private static final Logger LOGGER =
	        LogFactory.getLogger(CarroDAO.class);
	
	public Optional<Carro> findById(long id) {
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_FIND_BY_ID)) {
			stmt.setLong(1, id);
			try (ResultSet rs = stmt.executeQuery()) {			
				if (rs.next()) {
				    return Optional.of(mapToCarro(rs));
				}
				return Optional.empty();
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar carro pelo id: " + id, e);
		}
	}
	
	public List<Carro> findAll() {
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_FIND_ALL)) {
			try (ResultSet rs = stmt.executeQuery()) {
				return mapResultSet(rs);
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar todos os carros. ", e);
		}
	}

	public List<Carro> findByName(String name) {
		if (isEmpty(name)) {
			return Collections.emptyList();
		}

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_FIND_BY_NAME)) {
			stmt.setString(1, "%" + name.toLowerCase(Locale.ROOT) + "%");
			try (ResultSet rs = stmt.executeQuery()) {
				return mapResultSet(rs);
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar carro pelo nome: " + name, e);
		}
	}
	
	public List<Carro> findByTipo(String tipo){
		if (isEmpty(tipo)) {
			return Collections.emptyList();
		}

		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_FIND_BY_TIPO)) {
			stmt.setString(1, tipo.toLowerCase(Locale.ROOT));
			try (ResultSet rs = stmt.executeQuery()) {
				return mapResultSet(rs);
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao buscar carro pelo tipo: " + tipo,e);
		}
	}
	
	public Carro save(Carro carro) {
		if (carro == null) {
		    throw new IllegalArgumentException("Carro não pode ser nulo.");
		}
		LOGGER.info("Salvando carro: " + carro.getNome());
		boolean novoCarro = carro.getId() == null;
		try (Connection conn = getConnection();
				     PreparedStatement stmt = (novoCarro)
				             ? conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)
				             : conn.prepareStatement(SQL_UPDATE)) {
			bindCarro(stmt, carro);
			if (!novoCarro) {
			    stmt.setLong(8, carro.getId());
			}
			int count = stmt.executeUpdate();
			if (count == 0) {
			    throw new BancoDeDadosException("Nenhum carro foi salvo.");
			}
			if (novoCarro) {
				Long id = getGeneratedId(stmt);
				carro.setId(id);
			}
			return carro;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE,"Erro ao salvar carro: " + carro.getNome(), e);
			String operacao = novoCarro ? "inserir" : "alterar";
		    throw new BancoDeDadosException("Erro ao " + operacao + " carro: " + carro.getNome(), e);
		}

	}
	
	public boolean delete(long id) {
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(SQL_DELETE)) {
			LOGGER.info("Excluindo carro id=" + id);
			stmt.setLong(1, id);
			int count = stmt.executeUpdate();
	        return count > 0;
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao excluir carro pelo id: " + id, e);
		}
	}
	
	private void bindCarro(PreparedStatement stmt, Carro carro) throws SQLException {
	    stmt.setString(1, carro.getNome());
	    stmt.setString(2, carro.getDescricao());
	    stmt.setString(3, carro.getUrlFoto());
	    stmt.setString(4, carro.getUrlVideo());
	    stmt.setObject(5, carro.getLatitude());
	    stmt.setObject(6, carro.getLongitude());
	    stmt.setString(7, carro.getTipo());
	}

	private Carro mapToCarro(ResultSet rs) throws SQLException {
		Carro carro = new Carro();
		carro.setId(rs.getLong("id"));
		carro.setTipo(rs.getString("tipo"));
		carro.setNome(rs.getString("nome"));
		carro.setDescricao(rs.getString("descricao"));
		carro.setUrlFoto(rs.getString("url_foto"));
		carro.setUrlVideo(rs.getString("url_video"));
		carro.setLatitude(getNullableDouble(rs, "latitude"));
		carro.setLongitude(getNullableDouble(rs, "longitude"));
		
		return carro;
	}
	
	private long getGeneratedId(Statement stmt) {
		try (ResultSet rs = stmt.getGeneratedKeys()) {
			if (rs.next()) {
			    return rs.getLong(1);
			}
		} catch (SQLException e) {
			throw new BancoDeDadosException("Erro ao recuperar o id do carro: ", e);	
		}
		throw new BancoDeDadosException("N�o foi poss�vel recuperar o id gerado.");
	}
	
	private List<Carro> mapResultSet(ResultSet rs) throws SQLException {
	    List<Carro> carros = new ArrayList<>();

	    while (rs.next()) {
	        carros.add(mapToCarro(rs));
	    }

	    return carros;
	}
	
	private boolean isEmpty(String valor) {
	    return valor == null || valor.trim().isEmpty();
	}
	
	private Double getNullableDouble(ResultSet rs, String coluna) throws SQLException {
	    double valor = rs.getDouble(coluna);
	    return rs.wasNull() ? null : valor;
	}
}
