    public static Level toLevel(final String sArg, final Level defaultLevel) {
        if (sArg == null) {
            return defaultLevel;
        }
        final String s = sArg.toUpperCase(Locale.ROOT);
        switch (s) {
        case "ALL":
            return Level.ALL;
        case "DEBUG":
            return Level.DEBUG;
        case "INFO":
            return Level.INFO;
        case "WARN":
            return Level.WARN;
        case "ERROR":
            return Level.ERROR;
        case "FATAL":
            return Level.FATAL;
        case "OFF":
            return Level.OFF;
        case "TRACE":
            return Level.TRACE;
        default:
            return defaultLevel;
        }
    }
