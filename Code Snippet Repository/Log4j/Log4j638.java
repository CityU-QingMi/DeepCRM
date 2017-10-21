    @Override
    public void error(final String msg, final LogEvent event, final Throwable t) {
        final long current = System.nanoTime();
        if (current - lastException > EXCEPTION_INTERVAL || exceptionCount++ < MAX_EXCEPTIONS) {
            LOGGER.error(msg, t);
        }
        lastException = current;
        if (!appender.ignoreExceptions() && t != null && !(t instanceof AppenderLoggingException)) {
            throw new AppenderLoggingException(msg, t);
        }
    }
