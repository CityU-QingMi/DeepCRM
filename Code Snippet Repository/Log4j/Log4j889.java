    static ExceptionHandler<AsyncLoggerConfigDisruptor.Log4jEventWrapper> getAsyncLoggerConfigExceptionHandler() {
        final String cls = PropertiesUtil.getProperties().getStringProperty("AsyncLoggerConfig.ExceptionHandler");
        if (cls == null) {
            return new AsyncLoggerConfigDefaultExceptionHandler();
        }
        try {
            @SuppressWarnings("unchecked")
            final Class<? extends ExceptionHandler<AsyncLoggerConfigDisruptor.Log4jEventWrapper>> klass =
                    (Class<? extends ExceptionHandler<AsyncLoggerConfigDisruptor.Log4jEventWrapper>>) LoaderUtil.loadClass(cls);
            return klass.newInstance();
        } catch (final Exception ignored) {
            LOGGER.debug("Invalid AsyncLoggerConfig.ExceptionHandler value: error creating {}: ", cls, ignored);
            return new AsyncLoggerConfigDefaultExceptionHandler();
        }
    }
