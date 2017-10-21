    private void removeListeners(final String ctxName) {
        final StatusLogger logger = StatusLogger.getLogger();
        final Iterable<StatusListener> listeners = logger.getListeners();
        // Remove any StatusLoggerAdmin listeners already registered for this context
        for (final StatusListener statusListener : listeners) {
            if (statusListener instanceof StatusLoggerAdmin) {
                final StatusLoggerAdmin adminListener = (StatusLoggerAdmin) statusListener;
                if (ctxName != null && ctxName.equals(adminListener.contextName)) {
                    logger.removeListener(adminListener);
                }
            }
        }
    }
