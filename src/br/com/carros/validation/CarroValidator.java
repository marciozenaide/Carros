package br.com.carros.validation;

import br.com.carros.i18n.Messages;
import br.com.carros.model.Carro;

public class CarroValidator {

	private static final int TAMANHO_TEXTO = 255;
	private static final int LIMITE_LATITUDE = 90;
	private static final int LIMITE_LONGITUDE = 180;

	public ValidatorResult validar(Carro carro) {

		ValidatorResult result = new ValidatorResult();

		// obrigatórios
		validarObrigatorio("erro.nome.obrigatorio", carro.getNome(), result);
		validarObrigatorio("erro.descricao.obrigatorio", carro.getDescricao(), result);
		validarObrigatorio("erro.tipo.obrigatorio", carro.getTipo(), result);
		validarObrigatorio("erro.urlFoto.obrigatorio", carro.getUrlFoto(), result);
		validarObrigatorio("erro.urlVideo.obrigatorio", carro.getUrlVideo(), result);

		
		// tamanho
		validarTamanho("erro.nome.tamanho", carro.getNome(), TAMANHO_TEXTO, result);
		validarTamanho("erro.descricao.tamanho", carro.getDescricao(), TAMANHO_TEXTO, result);
		validarTamanho("erro.tipo.tamanho", carro.getTipo(),TAMANHO_TEXTO, result);
		validarTamanho("erro.urlFoto.tamanho", carro.getUrlFoto(),TAMANHO_TEXTO, result);
		validarTamanho("erro.urlVideo.tamanho", carro.getUrlVideo(),TAMANHO_TEXTO, result);
		
		// coordenadas
		validarCoordenada(carro.getLatitude(),LIMITE_LATITUDE,"erro.latitude.obrigatorio","erro.latitude.invalida",result);
		validarCoordenada(carro.getLongitude(),LIMITE_LONGITUDE,"erro.longitude.obrigatorio","erro.longitude.invalida",result);

		return result;

	}

	private void validarObrigatorio(String chave, String campo, ValidatorResult result) {
		if (campo == null || campo.trim().isEmpty()) {
			result.addErro(Messages.get(chave));
		}
	}

	private void validarTamanho(String chave, String campo, int tamanho, ValidatorResult result) {
		if (campo != null && campo.length() > tamanho) {
			result.addErro(Messages.get(chave, tamanho));
		}
	}
	
	private void validarCoordenada(Double valor, int limite, String chaveObrigatoria, String chaveInvalida, ValidatorResult result) {

	    if (valor == null) {
	        result.addErro(Messages.get(chaveObrigatoria));
	        return;
	    }

	    if (valor < -limite || valor > limite) {
	        result.addErro(Messages.get(chaveInvalida));
	    }
	}
	

}
