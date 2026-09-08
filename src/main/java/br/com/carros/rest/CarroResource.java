package br.com.carros.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.com.carros.model.Carro;
import br.com.carros.service.CarroService;

@Path("/carros")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarroResource {
	
	private final CarroService carroService;

    public CarroResource() {
        this(new CarroService());
    }

    public CarroResource(CarroService carroService) {
        this.carroService = carroService;
    }
	
	@GET
	public List<Carro> findAll() {
	    return carroService.findAll();
	}
	
	@GET
	@Path("{id}")
	public Carro get(@PathParam("id") Long id) {
		return carroService.findById(id).orElse(null);
	}
	
	@GET
	@Path("/tipo/{tipo}")
	public List<Carro> getByTipo(@PathParam("tipo") String tipo){
		return carroService.findByTipo(tipo);
	}
	
	@GET
	@Path("/nome/{nome}")
	public List<Carro> getByNome(@PathParam("nome") String nome){
		return carroService.findByName(nome);
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") Long id) {
		carroService.delete(id);
		return Response.ok("Carro deletado com sucesso").build();
	}
	
	@POST
	public Response post(Carro carro) {
		carroService.save(carro);
		return Response.ok("Carro salvo com sucesso").build();
	}
	
	@PUT
	public Response put(Carro carro) {
		carroService.save(carro);
		return Response.ok("Carro atualizado com sucesso").build();
	}
}
