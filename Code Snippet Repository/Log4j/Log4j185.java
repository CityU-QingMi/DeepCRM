    public Class<? extends LoggerContextFactory> loadLoggerContextFactory() {
        if (loggerContextFactoryClass != null) {
            return loggerContextFactoryClass;
        }
        if (className == null) {
            return null;
        }
        final ClassLoader loader = classLoader.get();
        if (loader == null) {
            return null;
        }
        try {
            final Class<?> clazz = loader.loadClass(className);
            if (LoggerContextFactory.class.isAssignableFrom(clazz)) {
                return clazz.asSubclass(LoggerContextFactory.class);
            }
        } catch (final Exception e) {
            LOGGER.error("Unable to create class {} specified in {}", className, url.toString(), e);
        }
        return null;
    }
