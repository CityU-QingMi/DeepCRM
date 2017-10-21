    protected String serializeToString(final Serializer serializer) {
        if (serializer == null) {
            return null;
        }
        final LoggerConfig rootLogger = getConfiguration().getRootLogger();
        // Using "" for the FQCN, does it matter?
        final LogEvent logEvent = getLogEventFactory().createEvent(rootLogger.getName(), null, Strings.EMPTY,
                rootLogger.getLevel(), null, null, null);
        return serializer.toSerializable(logEvent);
    }
