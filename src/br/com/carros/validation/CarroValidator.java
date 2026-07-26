package br.com.carros.validation;

import br.com.carros.model.Carro;

public class CarroValidator {

	public ValidatorResult validar(Carro carro) {

		ValidatorResult result = new ValidatorResult();

		if (carro.getNome() == null || carro.getNome().trim().isEmpty()) {
			result.addErro("Nome é obrigatório.");
		}

		if (carro.getDescricao() == null || carro.getDescricao().isEmpty()) {
			result.addErro("O descrição do carro deve ser preenchido");
		}

		if (carro.getTipo() == null || carro.getTipo().trim().isEmpty()) {
			result.addErro("Tipo é obrigatório.");
		}

		if (carro.getLatitude() != null && (carro.getLatitude() < -90 || carro.getLatitude() > 90)) {
			result.addErro("Latitude inválida.");
		}

		return result;

	}

}
