    protected void doLog(Logger logger, String message) {
        if (logLevel == null) {
            logger.info(message);
            return;
        }

        if ("debug".equalsIgnoreCase(logLevel)) {
            logger.debug(message);
        } else if ("info".equalsIgnoreCase(logLevel)) {
            logger.info(message);
        } else if ("warn".equalsIgnoreCase(logLevel)) {
            logger.warn(message);
        } else if ("error".equalsIgnoreCase(logLevel)) {
            logger.error(message);
        } else if ("fatal".equalsIgnoreCase(logLevel)) {
            logger.fatal(message);
        } else if ("trace".equalsIgnoreCase(logLevel)) {
            logger.trace(message);
        } else {
            throw new IllegalArgumentException("LogLevel [" + logLevel + "] is not supported");
        }
    }
