package br.com.carros.validation;

import br.com.carros.i18n.MessageKeys;
import br.com.carros.i18n.Messages;
import br.com.carros.model.Carro;

public class CarroValidator {

	public ValidatorResult validar(Carro carro) {

		ValidatorResult result = new ValidatorResult();

		// obrigat�rios
		validarObrigatorio(MessageKeys.ERRO_NOME_OBRIGATORIO, carro.getNome(), result);
		validarObrigatorio(MessageKeys.ERRO_DESCRICAO_OBRIGATORIO, carro.getDescricao(), result);
		validarObrigatorio(MessageKeys.ERRO_TIPO_OBRIGATORIO, carro.getTipo(), result);
		validarObrigatorio(MessageKeys.ERRO_URL_FOTO_OBRIGATORIO, carro.getUrlFoto(), result);
		validarObrigatorio(MessageKeys.ERRO_URL_VIDEO_OBRIGATORIO, carro.getUrlVideo(), result);

		
		// tamanho
		validarTamanho(MessageKeys.ERRO_NOME_TAMANHO, carro.getNome(), ValidationConstants.TAMANHO_TEXTO, result);
		validarTamanho(MessageKeys.ERRO_DESCRICAO_TAMANHO, carro.getDescricao(), ValidationConstants.TAMANHO_TEXTO, result);
		validarTamanho(MessageKeys.ERRO_TIPO_TAMANHO, carro.getTipo(),ValidationConstants.TAMANHO_TEXTO, result);
		validarTamanho(MessageKeys.ERRO_URL_FOTO_TAMANHO, carro.getUrlFoto(),ValidationConstants.TAMANHO_TEXTO, result);
		validarTamanho(MessageKeys.ERRO_URL_VIDEO_TAMANHO, carro.getUrlVideo(),ValidationConstants.TAMANHO_TEXTO, result);
		
		validarURL(MessageKeys.ERRO_URL_FOTO_INVALIDA, carro.getUrlFoto(), result);
		validarURL(MessageKeys.ERRO_URL_VIDEO_INVALIDA, carro.getUrlVideo(), result);
		
		// coordenadas
		validarCoordenada(carro.getLatitude(),ValidationConstants.LIMITE_LATITUDE,MessageKeys.ERRO_LATITUDE_OBRIGATORIO,MessageKeys.ERRO_LATITUDE_INVALIDA,result);
		validarCoordenada(carro.getLongitude(),ValidationConstants.LIMITE_LONGITUDE,MessageKeys.ERRO_LONGITUDE_OBRIGATORIO,MessageKeys.ERRO_LONGITUDE_INVALIDA,result);

		return result;

	}

	private void validarURL(String chave, String campo, ValidatorResult result) {
		if (campo == null || campo.trim().isEmpty() || !UrlValidator.isValidUrl(campo.trim())) {
			result.addErro(Messages.get(chave));
		}
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
	
	private void validarCoordenada(Double valor, double limite, String chaveObrigatoria, String chaveInvalida, ValidatorResult result) {

	    if (valor == null) {
	        result.addErro(Messages.get(chaveObrigatoria));
	        return;
	    }

	    if (valor < -limite || valor > limite) {
	        result.addErro(Messages.get(chaveInvalida));
	    }
	}
	

}
