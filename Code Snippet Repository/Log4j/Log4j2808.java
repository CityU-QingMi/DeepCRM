    private PrintStream setupStream(final Bundle api, final PrintStream newStream) throws ReflectiveOperationException {
        // use reflection to access the classes internals and in the context of the api bundle

        final Class<?> statusLoggerClass = api.loadClass("org.apache.logging.log4j.status.StatusLogger");

        final Field statusLoggerField = statusLoggerClass.getDeclaredField("STATUS_LOGGER");
        statusLoggerField.setAccessible(true);
        final Object statusLoggerFieldValue = statusLoggerField.get(null);

        final Field loggerField = statusLoggerClass.getDeclaredField("logger");
        loggerField.setAccessible(true);
        final Object loggerFieldValue = loggerField.get(statusLoggerFieldValue);

        final Class<?> simpleLoggerClass = api.loadClass("org.apache.logging.log4j.simple.SimpleLogger");

        final Field streamField = simpleLoggerClass.getDeclaredField("stream");
        streamField.setAccessible(true);

        final PrintStream oldStream = (PrintStream) streamField.get(loggerFieldValue);

        streamField.set(loggerFieldValue, newStream);

        return oldStream;
    }
