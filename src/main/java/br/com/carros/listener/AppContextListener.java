package br.com.carros.listener;

import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.carros.i18n.Messages;
import br.com.carros.util.LogFactory;

@WebListener
public class AppContextListener implements ServletContextListener {
	
	private static final Logger LOGGER = LogFactory.getLogger(AppContextListener.class);
	
	private static final String APP_NAME = "app.name";
	private static final String APP_VERSION = "app.version";
	private static final String APP_ENVIRONMENT = "app.environment";
	
	 @Override
	    public void contextInitialized(ServletContextEvent sce) {

	        LOGGER.info("Web Application is starting up...");
	        
	        ServletContext context = sce.getServletContext();

	        context.setAttribute("appName", Messages.get(APP_NAME));
	        context.setAttribute("environment", Messages.get(APP_VERSION));
	        context.setAttribute("appVersion", APP_ENVIRONMENT);

	    }

	    @Override
	    public void contextDestroyed(ServletContextEvent sce) {

	        LOGGER.info("Web Application is shutting down.");
	    }
}
