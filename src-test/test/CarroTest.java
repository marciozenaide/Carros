package test;

import java.util.List;
import java.util.Optional;

import br.com.carros.model.Carro;
import br.com.carros.service.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {
	private CarroService carroService = new CarroService();

	public void testListaCarros() {
		Carro carro = carroService.findById(999999L).orElse(null);
		assertNull("Esperado null",carro);
		
		assertTrue(carroService.findByName("").isEmpty());
		assertTrue(carroService.findByTipo("").isEmpty());
		
		List<Carro> carros = carroService.findAll();
		assertNotNull(carros);
		// Validar se encontrou algo
		assertFalse(carros.isEmpty());
		
		// Validar se não encontrou Tucker
		List<Carro> tucker = carroService.findByName("Tucker 1948");
		assertTrue(tucker.isEmpty());
		
		// Validar se encontrou Ferrari	
		List<Carro> ferraris = carroService.findByName("Ferrari F40");
		assertFalse(ferraris.isEmpty());
		Carro ferrari = ferraris.get(0);
		assertEquals("Ferrari F40", ferrari.getNome());
		
		// Validar se encontrou Bugatti
		List<Carro> bugattis = carroService.findByName("Bugatti Veyron");
		assertFalse(bugattis.isEmpty());
		Carro bugatti = bugattis.get(0);
		assertEquals("Bugatti Veyron", bugatti.getNome());
	}
	
	public void testSalvarAtualizarExcluirCarro() {
		//carroService.delete(42L);
		Carro carro = criarCarro();
		carroService.save(carro);
		
		//id do carro salvo
		long id = carro.getId();
		assertNotNull(id);
		
		//Busca no banco de dados para confirmar que o carro foi salvo
		Optional<Carro> carroOpt = carroService.findById(id);
		assertTrue(carroOpt.isPresent());
		carro = carroOpt.get();
		assertEquals("Carro", carro.getNome());
		
		//Atualiza o carro
		
		carro.setNome("Carro Update");
		carroService.save(carro);
		
		//Busca o carro novamente (vai estar atuaçizado)
		Carro carroAtualizado = carroService.findById(id).orElse(null);
		assertNotNull(carroAtualizado);
		assertEquals("Carro Update", carroAtualizado.getNome());
		
		//deleta o carro
		carroService.delete(id);
		
		//busca o carro novamente
		Optional<Carro> carroOpt2 = carroService.findById(id);
		assertFalse(carroOpt2.isPresent());
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
