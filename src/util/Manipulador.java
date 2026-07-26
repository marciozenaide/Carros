package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Manipulador {

	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		FileInputStream file = new FileInputStream("db.properties");
		props.load(file);
		return props;
	}
	
	public static void  main(String args[]) throws IOException {
		String url; //Variavel que guardará o login do servidor.
		String user; //Variavel que guardará o host do servidor.
		String password; //Variável que guardará o password do usúario.
		System.out.println("************Teste de leitura do arquivo de propriedades************");
		
		Properties prop = getProp();
		
		url = prop.getProperty("db.url");
		user = prop.getProperty("db.user");
		password = prop.getProperty("db.password");
		
		System.out.println("url = " + url);
		System.out.println("user = " + user);
		System.out.println("Password = " + password);
	}
}
