    @Override
    public Log4jTaglibLogger getLogger(final String name, final MessageFactory messageFactory) {
        // Note: This is the only method where we add entries to the 'loggerRegistry' ivar.
        Log4jTaglibLogger logger = this.loggerRegistry.getLogger(name, messageFactory);
        if (logger != null) {
            AbstractLogger.checkMessageFactory(logger, messageFactory);
            return logger;
        }

        synchronized (this.loggerRegistry) {
            logger = this.loggerRegistry.getLogger(name, messageFactory);
            if (logger == null) {
                final LoggerContext context = LogManager.getContext(false);
                final ExtendedLogger original = messageFactory == null ?
                        context.getLogger(name) : context.getLogger(name, messageFactory);
                // wrap a logger from an underlying implementation
                logger = new Log4jTaglibLogger(original, name, original.getMessageFactory());
                this.loggerRegistry.putIfAbsent(name, messageFactory, logger);
            }
        }

        return logger;
    }
