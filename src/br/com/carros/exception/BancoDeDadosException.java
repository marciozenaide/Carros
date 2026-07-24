package br.com.carros.exception;

public class BancoDeDadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BancoDeDadosException(String mensagem) {
		super(mensagem);
	}

	public BancoDeDadosException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
