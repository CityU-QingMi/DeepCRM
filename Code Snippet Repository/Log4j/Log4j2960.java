    static Log4jTaglibLogger resolveLogger(final Log4jTaglibLoggerContext context, final Object logger,
                                           final MessageFactory factory) throws JspException {
        if (logger instanceof Logger) {
            if (logger instanceof Log4jTaglibLogger) {
                return (Log4jTaglibLogger) logger;
            }
            if (logger instanceof AbstractLogger) {
                if (LOGGER.isInfoEnabled() && !WARNED_FOR.contains(logger)) {
                    LOGGER.info("Constructing new Log4jTaglibLogger from AbstractLogger {} name and message factory.",
                            logger.getClass().getName());
                    WARNED_FOR.add(logger);
                }
                final AbstractLogger original = (AbstractLogger) logger;
                return getLogger(context, original.getName(), original.getMessageFactory());
            }
            throw new JspException(
                    "Log4j Tag Library requires base logging system to extend Log4j AbstractLogger.");
        }
        if (logger instanceof String) {
            return getLogger(context, (String) logger, factory);
        }
        throw new JspException("Logger must be of type String or org.apache.logging.log4j.Logger.");
    }
