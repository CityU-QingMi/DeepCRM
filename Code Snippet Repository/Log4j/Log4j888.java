    static ExceptionHandler<RingBufferLogEvent> getAsyncLoggerExceptionHandler() {
        final String cls = PropertiesUtil.getProperties().getStringProperty("AsyncLogger.ExceptionHandler");
        if (cls == null) {
            return new AsyncLoggerDefaultExceptionHandler();
        }
        try {
            @SuppressWarnings("unchecked")
            final Class<? extends ExceptionHandler<RingBufferLogEvent>> klass =
                (Class<? extends ExceptionHandler<RingBufferLogEvent>>) LoaderUtil.loadClass(cls);
            return klass.newInstance();
        } catch (final Exception ignored) {
            LOGGER.debug("Invalid AsyncLogger.ExceptionHandler value: error creating {}: ", cls, ignored);
            return new AsyncLoggerDefaultExceptionHandler();
        }
    }
