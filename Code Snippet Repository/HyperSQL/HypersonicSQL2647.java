    public void logSevereEvent(String message, Throwable t) {

        getEventLogger();

        if (fwLogger != null) {
            fwLogger.severe(message, t);
        }

        if (appLog != null) {
            if (t == null) {
                appLog.logContext(SimpleLog.LOG_ERROR, message);
            } else {
                appLog.logContext(t, message, SimpleLog.LOG_ERROR);
            }
        }
    }
