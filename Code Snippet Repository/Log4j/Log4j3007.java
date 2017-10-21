    private boolean isEnabledFor(final Level level, final Marker marker) {
        final org.slf4j.Marker slf4jMarker = getMarker(marker);
        switch (level.getStandardLevel()) {
            case DEBUG :
                return logger.isDebugEnabled(slf4jMarker);
            case TRACE :
                return logger.isTraceEnabled(slf4jMarker);
            case INFO :
                return logger.isInfoEnabled(slf4jMarker);
            case WARN :
                return logger.isWarnEnabled(slf4jMarker);
            case ERROR :
                return logger.isErrorEnabled(slf4jMarker);
            default :
                return logger.isErrorEnabled(slf4jMarker);

        }
    }
