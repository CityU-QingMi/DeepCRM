    private static boolean isLoggerEnabled(Logger logger, String level) {
        if ("debug".equalsIgnoreCase(level)) {
            return logger.isDebugEnabled();
        } else if ("info".equalsIgnoreCase(level)) {
            return logger.isInfoEnabled();
        } else if ("warn".equalsIgnoreCase(level)) {
            return logger.isWarnEnabled();
        } else if ("error".equalsIgnoreCase(level)) {
            return logger.isErrorEnabled();
        } else if ("fatal".equalsIgnoreCase(level)) {
            return logger.isFatalEnabled();
        } else if ("trace".equalsIgnoreCase(level)) {
            return logger.isTraceEnabled();
        } else {
            throw new IllegalArgumentException("LogLevel [" + level + "] is not supported");
        }
    }
