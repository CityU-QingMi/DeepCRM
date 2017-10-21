    public void logDetailEvent(String message) {

        getEventLogger();

        if (fwLogger != null) {
            fwLogger.finest(message);
        }

        if (appLog != null) {
            appLog.logContext(SimpleLog.LOG_DETAIL, message);
        }
    }
