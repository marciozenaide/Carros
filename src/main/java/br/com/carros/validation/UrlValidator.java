package br.com.carros.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UrlValidator {

	private static final String URL_REGEX = "^(https?://)?([a-zA-Z0-9]([a-zA-Z0-9\\-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,63}(:\\d{1,5})?(/\\S*)?$";

	private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);

	public static boolean isValidUrl(String url) {
		
		if (url == null) {
			return false;
		}
		Matcher matcher = URL_PATTERN.matcher(url);
		return matcher.matches();
	}
}
