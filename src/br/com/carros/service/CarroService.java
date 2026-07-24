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

	public List<Carro> listar() {
		return carroDAO.findAll();
	}

	public Optional<Carro> buscarPorId(Long id) {
		return carroDAO.findById(id);
	}

	public List<Carro> buscarPorNome(String nome) {
		return carroDAO.findByName(nome);
	}

	public List<Carro> buscarPorTipo(String tipo) {
		return carroDAO.findByTipo(tipo);
	}

	public void salvar(Carro carro) {
		carroDAO.save(carro);
	}

	public boolean excluir(Long id) {
		return carroDAO.delete(id);
	}

}
