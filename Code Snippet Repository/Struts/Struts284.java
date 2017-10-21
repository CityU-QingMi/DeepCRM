    protected Logger getLoggerToUse() {
        if (logCategory != null) {
            if (categoryLogger == null) {
                // init category logger
                categoryLogger = LogManager.getLogger(logCategory);
                if (logLevel == null) {
                    logLevel = "info"; // use info as default if not provided
                }
            }
            return categoryLogger;
        }

        return LOG;
    }
