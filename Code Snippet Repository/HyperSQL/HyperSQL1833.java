    public void logWarningEvent(String message, Throwable t) {

        getEventLogger();

        if (fwLogger != null) {
            fwLogger.warning(message, t);
        }

        appLog.logContext(t, message, SimpleLog.LOG_ERROR);
    }
