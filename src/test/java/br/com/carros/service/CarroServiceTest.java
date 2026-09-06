package br.com.carros.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.carros.model.Carro;

public class CarroServiceTest {

	private FakeCarroDAO carroDAO;
	private CarroService carroService;

	@BeforeEach
	void prepararTeste() {
		carroDAO = new FakeCarroDAO();
		carroService = new CarroService(carroDAO);
	}

	@Test
	void deveBuscarTodosOsCarros() {
		Carro carro1 = criarCarro(1L, "Ferrari");
		Carro carro2 = criarCarro(2L, "Fusca");

		carroDAO.carros = Arrays.asList(carro1, carro2);

		List<Carro> resultado = carroService.findAll();

		assertEquals(2, resultado.size());
		assertEquals("Ferrari", resultado.get(0).getNome());
		assertEquals("Fusca", resultado.get(1).getNome());
	}

	@Test
	void deveBuscarCarroPorId() {
		Carro carro = criarCarro(1L, "Civic");
		carroDAO.carroPorId = Optional.of(carro);

		Optional<Carro> resultado = carroService.findById(1L);

		assertTrue(resultado.isPresent());
		assertSame(carro, resultado.get());
	}

	@Test
	void deveRetornarVazioQuandoCarroNaoForEncontradoPorId() {
		carroDAO.carroPorId = Optional.empty();

		Optional<Carro> resultado = carroService.findById(999L);

		assertTrue(carroDAO.findByIdChamado);
		assertFalse(resultado.isPresent());
	}

	@Test
	void deveBuscarCarrosPorNome() {
		Carro carro = criarCarro(1L, "Ferrari Roma");
		carroDAO.carrosPorNome = Collections.singletonList(carro);

		List<Carro> resultado = carroService.findByName("Roma");

		assertTrue(carroDAO.findByNameChamado);
		assertEquals(1, resultado.size());
		assertSame(carro, resultado.get(0));
	}

	@Test
	void deveBuscarCarrosPorTipo() {
		Carro carro = criarCarro(1L, "Ferrari");
		carroDAO.carrosPorTipo = Collections.singletonList(carro);

		List<Carro> resultado = carroService.findByTipo("esportivo");

		assertTrue(carroDAO.findByTipoChamado);
		assertEquals(1, resultado.size());
		assertSame(carro, resultado.get(0));
	}

	@Test
	void deveSalvarCarro() {
		Carro carro = criarCarro(null, "Fusca");
		Carro carroSalvo = criarCarro(10L, "Fusca");

		carroDAO.carroSalvo = carroSalvo;

		Carro resultado = carroService.save(carro);

		assertTrue(carroDAO.saveChamado);
		assertSame(carroSalvo, resultado);
		assertSame(carro, carroDAO.carroRecebido);
	}

	@Test
	void deveExcluirCarro() {
		carroDAO.resultadoDelete = true;

		boolean resultado = carroService.delete(1L);

		assertTrue(carroDAO.deleteChamado);
		assertTrue(resultado);
		assertEquals(Long.valueOf(1L), carroDAO.idRecebido);
	}

	private Carro criarCarro(Long id, String nome) {
		Carro carro = new Carro();
		carro.setId(id);
		carro.setNome(nome);
		carro.setDescricao("Descricao");
		carro.setUrlFoto("url foto");
		carro.setUrlVideo("url video");
		carro.setLatitude(10D);
		carro.setLongitude(10D);
		carro.setTipo("tipo");
		return carro;
	}

}
