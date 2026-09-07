package br.com.carros.rest;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class RestApplication extends ResourceConfig {

	public RestApplication() {
		packages("br.com.carros.rest");
	}
}
