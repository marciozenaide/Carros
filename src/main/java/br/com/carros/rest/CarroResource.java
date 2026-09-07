package br.com.carros.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.carros.model.Carro;
import br.com.carros.service.CarroService;

@Path("/carros")
public class CarroResource {
	
	private final CarroService carroService;

    public CarroResource() {
        this(new CarroService());
    }

    public CarroResource(CarroService carroService) {
        this.carroService = carroService;
    }
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Carro> findAll() {
	    return carroService.findAll();
	}

}
