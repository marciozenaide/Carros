package br.com.carros.service;

import java.util.List;
import java.util.Optional;

import br.com.carros.dao.CarroDAO;
import br.com.carros.model.Carro;

public class CarroService {

	private final CarroDAO carroDAO;

    public CarroService() {
        this(new CarroDAO());
    }

    public CarroService(CarroDAO carroDAO) {
        this.carroDAO = carroDAO;
    }

	public List<Carro> findAll() {
		return carroDAO.findAll();
	}

	public Optional<Carro> findById(Long id) {
		return carroDAO.findById(id);
	}

	public List<Carro> findByName(String nome) {
		return carroDAO.findByName(nome);
	}

	public List<Carro> findByTipo(String tipo) {
		return carroDAO.findByTipo(tipo);
	}
	
	public Carro save(Carro carro) {
	    return carroDAO.save(carro);
	}

	public boolean delete(Long id) {
		return carroDAO.delete(id);
	}

}
