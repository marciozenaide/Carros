package br.com.carros.validation;

import br.com.carros.model.Carro;

public class CarroValidator {

	private static final int TAMANHO_TEXTO = 255;
	private static final int LIMITE_LATITUDE = 90;
	private static final int LIMITE_LONGITUDE = 180;

	public ValidatorResult validar(Carro carro) {

		ValidatorResult result = new ValidatorResult();

		// obrigatórios
		validarObrigatorio("O nome é obrigatório.", carro.getNome(), result);
		validarObrigatorio("A descrição é obrigatória.", carro.getDescricao(), result);
		validarObrigatorio("O tipo é obrigatório.", carro.getTipo(), result);
		validarObrigatorio("A URL da foto é obrigatória.", carro.getUrlFoto(), result);
		validarObrigatorio("A URL do vídeo é obrigatória.", carro.getUrlVideo(), result);

		// tamanho
		validarTamanho("O nome do carro deve conter no máximo %s caracteres.", carro.getNome(),
				TAMANHO_TEXTO, result);
		validarTamanho("A descrição do carro deve conter no máximo %s caracteres.", carro.getDescricao(),
				TAMANHO_TEXTO, result);
		validarTamanho("O tipo do carro deve conter no máximo %s caracteres.", carro.getTipo(),
				TAMANHO_TEXTO, result);
		validarTamanho("A URL da foto deve conter no máximo %s caracteres.", carro.getUrlFoto(),
				TAMANHO_TEXTO, result);
		validarTamanho("A URL do vídeo deve conter no máximo %s caracteres.", carro.getUrlVideo(),
				TAMANHO_TEXTO, result);
		
		// coordenadas
		validarCoordenada("Latitude", carro.getLatitude(), LIMITE_LATITUDE, result);
		validarCoordenada("Longitude", carro.getLongitude(), LIMITE_LONGITUDE, result);

		return result;

	}

	private void validarObrigatorio(String valor, String campo, ValidatorResult result) {
		if (campo == null || campo.trim().isEmpty()) {
			result.addErro(valor);
		}
	}

	private void validarTamanho(String valor, String campo, int tamanho, ValidatorResult result) {
		if (campo != null && campo.length() > tamanho) {
			result.addErro(String.format(valor, tamanho));
		}
	}
	
	
	private void validarCoordenada(String nome, Double valor, int limite, ValidatorResult result) {
		 if (valor == null) {
		        result.addErro(nome + " é obrigatória.");
		        return;
		    }

		    if (valor < -limite || valor > limite) {
		        result.addErro(nome + " inválida.");
		    }
	}
	

}
