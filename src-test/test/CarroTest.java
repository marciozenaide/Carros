package test;

import java.util.List;

import br.com.carros.model.Carro;
import br.com.carros.service.CarroService;
import junit.framework.TestCase;

public class CarroTest extends TestCase {
	private CarroService carroService = new CarroService();
	public void testListaCarros() {
		List<Carro> carros = carroService.listar();
		assertNotNull(carros);
		assertTrue(carros.size()>0);
		
	}
}
