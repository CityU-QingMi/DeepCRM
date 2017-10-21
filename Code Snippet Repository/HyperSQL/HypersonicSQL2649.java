    public void logInfoEvent(String message) {

        getEventLogger();

        if (fwLogger != null) {
            fwLogger.info(message);
        }

        appLog.logContext(SimpleLog.LOG_NORMAL, message);
    }
