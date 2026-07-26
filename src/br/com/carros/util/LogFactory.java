package br.com.carros.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogFactory {
	 private LogFactory() {
	    }

	    public static Logger getLogger(Class<?> clazz) {
	        Logger logger = Logger.getLogger(clazz.getName());
	        logger.setLevel(Level.ALL);
	        return logger;
	    }
}
