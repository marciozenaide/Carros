package br.com.carros.validation;

public final class ValidationConstants {
	
	private ValidationConstants() {}
	
	public static final int TAMANHO_TEXTO = 255;
	public static final double LIMITE_LATITUDE = 90;
	public static final double LIMITE_LONGITUDE = 180;
	private static final int TAMANHO_EXTRA_URL = "https://.com.br".length();
	public static final int LIMITE_URL = ValidationConstants.TAMANHO_TEXTO - TAMANHO_EXTRA_URL;

}
