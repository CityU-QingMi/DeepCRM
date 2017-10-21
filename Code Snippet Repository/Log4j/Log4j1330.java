    public static Severity getSeverity(final Level level) {
        switch (level.getStandardLevel()) {
            case ALL:
                return DEBUG;
            case TRACE:
                return DEBUG;
            case DEBUG:
                return DEBUG;
            case INFO:
                return INFO;
            case WARN:
                return WARNING;
            case ERROR:
                return ERROR;
            case FATAL:
                return ALERT;
            case OFF:
                return EMERG;
        }
        return DEBUG;
    }
