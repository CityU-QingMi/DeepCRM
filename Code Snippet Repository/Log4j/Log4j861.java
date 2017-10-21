    @Override
    public void logMessage(final String fqcn, final Level level, final Marker marker, final Message message,
            final Throwable thrown) {

        if (loggerDisruptor.isUseThreadLocals()) {
            logWithThreadLocalTranslator(fqcn, level, marker, message, thrown);
        } else {
            // LOG4J2-1172: avoid storing non-JDK classes in ThreadLocals to avoid memory leaks in web apps
            logWithVarargTranslator(fqcn, level, marker, message, thrown);
        }
    }
