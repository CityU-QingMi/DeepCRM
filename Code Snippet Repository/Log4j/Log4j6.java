    public static Level toLevel(final int val, final Level defaultLevel) {
        switch (val) {
            case ALL_INT:
                return ALL;
            case DEBUG_INT:
                return Level.DEBUG;
            case INFO_INT:
                return Level.INFO;
            case WARN_INT:
                return Level.WARN;
            case ERROR_INT:
                return Level.ERROR;
            case FATAL_INT:
                return Level.FATAL;
            case OFF_INT:
                return OFF;
            case TRACE_INT:
                return Level.TRACE;
            default:
                return defaultLevel;
        }
    }
