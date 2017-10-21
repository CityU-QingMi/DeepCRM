    public final Level getEffectiveLevel() {
        switch (logger.getLevel().getStandardLevel()) {
        case ALL:
            return Level.ALL;
        case TRACE:
            return Level.TRACE;
        case DEBUG:
            return Level.DEBUG;
        case INFO:
            return Level.INFO;
        case WARN:
            return Level.WARN;
        case ERROR:
            return Level.ERROR;
        case FATAL:
            return Level.FATAL;
        case OFF:
            return Level.OFF;
        default:
            // TODO Should this be an IllegalStateException?
            return Level.OFF;
        }
    }
