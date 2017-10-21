    protected void doLog(Logger logger, Exception e) {
    	if (logLevel == null) {
    		logger.debug(e.getMessage(), e);
    		return;
    	}
    	
    	if ("trace".equalsIgnoreCase(logLevel)) {
    		logger.trace(e.getMessage(), e);
    	} else if ("debug".equalsIgnoreCase(logLevel)) {
    		logger.debug(e.getMessage(), e);
    	} else if ("info".equalsIgnoreCase(logLevel)) {
    		logger.info(e.getMessage(), e);
    	} else if ("warn".equalsIgnoreCase(logLevel)) {
    		logger.warn(e.getMessage(), e);
    	} else if ("error".equalsIgnoreCase(logLevel)) {
    		logger.error(e.getMessage(), e);
    	} else if ("fatal".equalsIgnoreCase(logLevel)) {
    		logger.fatal(e.getMessage(), e);
    	} else {
    		throw new IllegalArgumentException("LogLevel [" + logLevel + "] is not supported");
    	}
    }
