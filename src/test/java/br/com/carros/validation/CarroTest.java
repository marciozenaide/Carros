package br.com.carros.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.carros.config.DatabaseTestConfig;
import br.com.carros.model.Carro;
import br.com.carros.service.CarroService;

public class CarroTest {

	private CarroService carroService = new CarroService();

	/*
	 * Abre a conexão, lê o schema.sql e executa todos os comandos SQL.
	 * Executado uma única vez antes de todos os testes.
	 */
	@BeforeAll
	static void prepararBanco() throws Exception {
		DatabaseTestConfig.initializeDatabase();
	}
	
	/*
	 * Executa antes de cada teste para restaurar os dados iniciais.
	 * Lê o schema.sql, mas não executa o CREATE TABLE.
	 */
	@BeforeEach
	void resetarBanco() throws Exception {
	    DatabaseTestConfig.resetDatabase();
	}

	@Test
	public void deveRetornarNullQuandoIdNaoExistir() {
		Carro carro = carroService.findById(999999L).orElse(null);
		assertNull(carro);
	}

	@Test
	public void deveRetornarListaDeCarros() {
		List<Carro> carros = carroService.findAll();
		assertNotNull(carros);
		assertFalse(carros.isEmpty());
	}

	@Test
	public void deveRetornarEmptyQuandoNomeNaoExistir() {
		List<Carro> carros = carroService.findByName("Tucker 1948");
		assertTrue(carros.isEmpty());
	}

	@Test
	public void deveEncontrarCarroPeloNome() {
		List<Carro> carros = carroService.findByName("Ferrari F40");
		assertFalse(carros.isEmpty());
		Carro carro = carros.get(0);
		assertEquals("Ferrari F40", carro.getNome());
	}

	@Test
	public void deveRetornarEmptyQuandoTipoNaoExistir() {
		List<Carro> carros = carroService.findByTipo("");
		assertTrue(carros.isEmpty());
	}

	@Test
	public void deveSalvarCarro() {
		Carro carro = criarCarro();
		carroService.save(carro);
		assertTrue(carro.getId() > 0);
		Optional<Carro> carroOpt = carroService.findById(carro.getId());
		assertTrue(carroOpt.isPresent());
		assertEquals("Carro", carroOpt.get().getNome());
	}

	@Test
	public void deveAtualizarCarro() {
		Carro carro = criarCarro();
		carroService.save(carro);
		long id = carro.getId();
		carro.setNome("Carro Update");
		carroService.save(carro);
		Carro carroAtualizado = carroService.findById(id).orElse(null);
		assertNotNull(carroAtualizado);
		assertEquals("Carro Update", carroAtualizado.getNome());
	}

	@Test
	public void deveExcluirCarro() {
		Carro carro = criarCarro();
		carroService.save(carro);
		long id = carro.getId();
		carroService.delete(id);
		Optional<Carro> carroOpt = carroService.findById(id);
		assertFalse(carroOpt.isPresent());
	}

	private Carro criarCarro() {
		Carro carro = new Carro();
		carro.setNome("Carro");
		carro.setDescricao("Carro descricao");
		carro.setUrlFoto("url foto aqui");
		carro.setUrlVideo("url video aqui");
		carro.setLatitude(10D);
		carro.setLongitude(10D);
		carro.setTipo("tipo");
		return carro;
	}
}
