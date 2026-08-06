package br.com.carros.i18n;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public final class Messages {
	
	private Messages() {
	}
	
	private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

	public static String get(String chave) {
		return bundle.getString(chave);
	}
	
	public static String get(String chave, Object... args) {
	    return MessageFormat.format(bundle.getString(chave), args);
	}
}
