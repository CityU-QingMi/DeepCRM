    @Override
    public void append(final LogEvent event) {
        final String name = event.getLoggerName();
        if (name != null) {
            for (final String pkg : EXCLUDED_PACKAGES) {
                if (name.startsWith(pkg)) {
                    return;
                }
            }
        }
        final FlumeEvent flumeEvent = factory.createEvent(event, mdcIncludes, mdcExcludes, mdcRequired, mdcPrefix,
            eventPrefix, compressBody);
        flumeEvent.setBody(getLayout().toByteArray(flumeEvent));
        manager.send(flumeEvent);
    }
