    @Override
    public void logMessage(final String fqcn, final Level level, final Marker marker, final Message message, final Throwable t) {
        if (locationAwareLogger != null) {
            if (message instanceof LoggerNameAwareMessage) {
                ((LoggerNameAwareMessage) message).setLoggerName(getName());
            }
            locationAwareLogger.log(getMarker(marker), fqcn, convertLevel(level), message.getFormattedMessage(),
                    message.getParameters(), t);
        } else {
            switch (level.getStandardLevel()) {
                case DEBUG :
                    logger.debug(getMarker(marker), message.getFormattedMessage(), message.getParameters(), t);
                    break;
                case TRACE :
                    logger.trace(getMarker(marker), message.getFormattedMessage(), message.getParameters(), t);
                    break;
                case INFO :
                    logger.info(getMarker(marker), message.getFormattedMessage(), message.getParameters(), t);
                    break;
                case WARN :
                    logger.warn(getMarker(marker), message.getFormattedMessage(), message.getParameters(), t);
                    break;
                case ERROR :
                    logger.error(getMarker(marker), message.getFormattedMessage(), message.getParameters(), t);
                    break;
                default :
                    logger.error(getMarker(marker), message.getFormattedMessage(), message.getParameters(), t);
                    break;
            }
        }
    }
