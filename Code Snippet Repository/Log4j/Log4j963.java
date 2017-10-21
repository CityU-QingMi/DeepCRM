    @PerformanceSensitive("")
    public void log(final String loggerName, final String fqcn, final Marker marker, final Level level,
            final Message data, final Throwable t) {
        List<Property> props = null;
        if (!propertiesRequireLookup) {
            props = properties;
        } else {
            if (properties != null) {
                props = new ArrayList<>(properties.size());
                final LogEvent event = Log4jLogEvent.newBuilder()
                        .setMessage(data)
                        .setMarker(marker)
                        .setLevel(level)
                        .setLoggerName(loggerName)
                        .setLoggerFqcn(fqcn)
                        .setThrown(t)
                        .build();
                for (int i = 0; i < properties.size(); i++) {
                    final Property prop = properties.get(i);
                    final String value = prop.isValueNeedsLookup() // since LOG4J2-1575
                            ? config.getStrSubstitutor().replace(event, prop.getValue()) //
                            : prop.getValue();
                    props.add(Property.createProperty(prop.getName(), value));
                }
            }
        }
        final LogEvent logEvent = logEventFactory.createEvent(loggerName, marker, fqcn, level, data, props, t);
        try {
            log(logEvent);
        } finally {
            // LOG4J2-1583 prevent scrambled logs when logging calls are nested (logging in toString())
            ReusableLogEventFactory.release(logEvent);
        }
    }
