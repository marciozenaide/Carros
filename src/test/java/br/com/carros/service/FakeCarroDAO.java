package br.com.carros.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import br.com.carros.dao.CarroDAO;
import br.com.carros.model.Carro;

/**
 * Fake manual do DAO.
 *
 * Não acessa banco de dados, ConnectionFactory ou H2. Serve somente para
 * controlar as respostas usadas pelo CarroServiceTest.
 */
public final class FakeCarroDAO extends CarroDAO {

	protected List<Carro> carros = Collections.emptyList();
	protected Optional<Carro> carroPorId = Optional.empty();
	protected List<Carro> carrosPorNome = Collections.emptyList();
	protected List<Carro> carrosPorTipo = Collections.emptyList();

	protected Carro carroRecebido;
	protected Carro carroSalvo;
	protected Long idRecebido;
	protected boolean resultadoDelete;

	protected boolean findAllChamado;
	protected boolean findByIdChamado;
	protected boolean findByNameChamado;
	protected boolean findByTipoChamado;
	protected boolean saveChamado;
	protected boolean deleteChamado;

	@Override
	public List<Carro> findAll() {
		findAllChamado = true;
		return carros;
	}

	@Override
	public Optional<Carro> findById(long id) {
		findByIdChamado = true;
		return carroPorId;
	}

	@Override
	public List<Carro> findByName(String nome) {
		findByNameChamado = true;
		return carrosPorNome;
	}

	@Override
	public List<Carro> findByTipo(String tipo) {
		findByTipoChamado = true;
		return carrosPorTipo;
	}

	@Override
	public Carro save(Carro carro) {
		saveChamado = true;
		carroRecebido = carro;
		return carroSalvo;
	}

	@Override
	public boolean delete(long id) {
		deleteChamado = true;
		idRecebido = id;
		return resultadoDelete;
	}

}
