package br.com.carros.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.carros.config.DatabaseTestConfig;
import br.com.carros.dao.CarroDAO;
import br.com.carros.exception.BancoDeDadosException;
import br.com.carros.model.Carro;

public class CarroDAOTest {
	
	private final CarroDAO carroDAO = new CarroDAO();

	/*
	 * Abre a conexão, lê o schema.sql e executa todos os comandos SQL. Executado
	 * uma única vez antes de todos os testes.
	 */
	@BeforeAll
	static void prepararBanco() throws Exception {
		DatabaseTestConfig.initializeDatabase();
	}

	/*
	 * Executa antes de cada teste para restaurar os dados iniciais. Lê o
	 * schema.sql, mas não executa o CREATE TABLE.
	 */
	@BeforeEach
	void resetarBanco() throws Exception {
		DatabaseTestConfig.resetDatabase();
	}

    @Test
	public void deveSalvarNovoCarroEGerarId() {
		Carro carro = criarCarro();
		carro.setNome("Fusca");
		carro.setTipo("classico");

		Carro salvo = carroDAO.save(carro);

		assertNotNull(salvo.getId(), "O ID do carro deveria ter sido gerado.");
		assertEquals("Fusca", salvo.getNome());
	}

    @Test
    public void deveBuscarCarroPorId() {
        // Cenário: Salva um carro primeiro
    	Carro carro = criarCarro();
        carro.setNome("Civic");
        carroDAO.save(carro);

        // Ação
        Optional<Carro> encontrado = carroDAO.findById(carro.getId());

        // Verificação
        assertTrue(encontrado.isPresent());
        assertEquals("Civic", encontrado.get().getNome());
    }

    @Test
    public void deveRetornarVazioQuandoIdNaoExistir() {
        Optional<Carro> encontrado = carroDAO.findById(999L);
        assertFalse(encontrado.isPresent());
    }

    @Test
    public void deveBuscarCarrosPorNomeIgnorandoCase() {
        Carro c1 = criarCarro();
        c1.setNome("Ferrari Roma"); 
        carroDAO.save(c1);
        Carro c2 = criarCarro();
        c2.setNome("Fusca"); 
        carroDAO.save(c2);

        List<Carro> resultado = carroDAO.findByName("ROMA");

        assertEquals(1, resultado.size());
        assertEquals("Ferrari Roma", resultado.get(0).getNome());
    }

    @Test
    public void deveRetornarListaVaziaQuandoNomeForNuloOuVazio() {
        List<Carro> resultadoComNulo = carroDAO.findByName(null);
        List<Carro> resultadoComVazio = carroDAO.findByName("   ");

        assertTrue(resultadoComNulo.isEmpty());
        assertTrue(resultadoComVazio.isEmpty());
    }

    @Test
    public void deveAtualizarCarroExistente() {
        Carro carro = criarCarro();
        carro.setNome("Palio");
        carroDAO.save(carro);

        // Altera dados
        carro.setNome("Palio Weekend");
        carroDAO.save(carro);

        Optional<Carro> atualizado = carroDAO.findById(carro.getId());
        assertEquals("Palio Weekend", atualizado.get().getNome());
    }

    @Test
    public void deveExcluirCarroComSucesso() {
        Carro carro = criarCarro();
        carro.setNome("Gol");
        carroDAO.save(carro);

        boolean deletado = carroDAO.delete(carro.getId());

        assertTrue(deletado);
        assertFalse(carroDAO.findById(carro.getId()).isPresent());
    }

    @Test
    public void deveLancarExcecaoAoSalvarCarroNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            carroDAO.save(null);
        });
    }
    
    @Test
    void deveBuscarCarrosPorTipoIgnorandoCase() {
        Carro c1 = criarCarro();
        c1.setNome("Ferrari");
        c1.setTipo("esportivo");
        carroDAO.save(c1);

        Carro c2 = criarCarro();
        c2.setNome("Fusca");
        c2.setTipo("classico");
        carroDAO.save(c2);

        List<Carro> resultado = carroDAO.findByTipo("CLASSICO");

        assertEquals(1, resultado.size());
        assertEquals("Fusca", resultado.get(0).getNome());
    }
	
    @Test
    void deveRetornarListaVaziaQuandoTipoForNuloOuVazio() {
        assertTrue(carroDAO.findByTipo(null).isEmpty());
        assertTrue(carroDAO.findByTipo("   ").isEmpty());
    }
    
    @Test
    void deveBuscarTodosOsCarros() {
        List<Carro> carros = carroDAO.findAll();

        assertEquals(2, carros.size());
    }
    
    @Test
    void deveRetornarFalseAoExcluirCarroInexistente() {
        boolean deletado = carroDAO.delete(999L);

        assertFalse(deletado);
    }
    
    @Test
    void deveLancarExcecaoAoAtualizarCarroInexistente() {
        Carro carro = criarCarro();
        carro.setId(999L);

        assertThrows(BancoDeDadosException.class, () -> carroDAO.save(carro));
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
