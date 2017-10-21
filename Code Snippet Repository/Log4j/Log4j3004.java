    private int convertLevel(final Level level) {
        switch (level.getStandardLevel()) {
            case DEBUG :
                return LocationAwareLogger.DEBUG_INT;
            case TRACE :
                return LocationAwareLogger.TRACE_INT;
            case INFO :
                return LocationAwareLogger.INFO_INT;
            case WARN :
                return LocationAwareLogger.WARN_INT;
            case ERROR :
                return LocationAwareLogger.ERROR_INT;
            default :
                return LocationAwareLogger.ERROR_INT;
        }
    }
