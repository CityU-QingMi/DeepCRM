    @Override
    public void logMessage(final String fqcn, final Level level, final Marker marker, final Message msg,
            final Throwable t) {
        StackTraceElement element = null;
        if (fqcn != null) {
            element = getStackTraceElement(fqcn, Thread.currentThread().getStackTrace());
        }
        final StatusData data = new StatusData(element, level, msg, t, null);
        msgLock.lock();
        try {
            messages.add(data);
        } finally {
            msgLock.unlock();
        }
        // LOG4J2-1813 if system property "log4j2.debug" is defined, all status logging is enabled
        if (isDebugPropertyEnabled()) {
            logger.logMessage(fqcn, level, marker, msg, t);
        } else {
            if (listeners.size() > 0) {
                for (final StatusListener listener : listeners) {
                    if (data.getLevel().isMoreSpecificThan(listener.getStatusLevel())) {
                        listener.log(data);
                    }
                }
            } else {
                logger.logMessage(fqcn, level, marker, msg, t);
            }
        }
    }
