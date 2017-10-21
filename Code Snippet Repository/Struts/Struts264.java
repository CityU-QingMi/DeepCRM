    protected void handleLogging(Exception e) {
    	if (logCategory != null) {
        	if (categoryLogger == null) {
        		// init category logger
        		categoryLogger = LogManager.getLogger(logCategory);
        	}
        	doLog(categoryLogger, e);
    	} else {
    		doLog(LOG, e);
    	}
    }
