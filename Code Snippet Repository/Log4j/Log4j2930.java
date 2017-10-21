    private static Level getLevel(final int i) {
        switch (i) {
        case TRACE_INT:
            return Level.TRACE;
        case DEBUG_INT:
            return Level.DEBUG;
        case INFO_INT:
            return Level.INFO;
        case WARN_INT:
            return Level.WARN;
        case ERROR_INT:
            return Level.ERROR;
        }
        return Level.ERROR;
    }
