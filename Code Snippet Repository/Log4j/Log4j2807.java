    private void log(final Bundle dummy) throws ReflectiveOperationException {
        // use reflection to log in the context of the dummy bundle

        final Class<?> logManagerClass = dummy.loadClass("org.apache.logging.log4j.LogManager");
        final Method getLoggerMethod = logManagerClass.getMethod("getLogger", Class.class);

        final Class<?> loggerClass = dummy.loadClass("org.apache.logging.log4j.configuration.CustomConfiguration");

        final Object logger = getLoggerMethod.invoke(null, loggerClass);
        final Method errorMethod = logger.getClass().getMethod("error", Object.class);

        errorMethod.invoke(logger, "Test OK");
    }
