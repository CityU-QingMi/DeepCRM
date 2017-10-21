    @Override
    public LogEvent rewrite(final LogEvent source) {
        final Map<String, String> props = new HashMap<>(source.getContextData().toMap());
        for (final Map.Entry<Property, Boolean> entry : properties.entrySet()) {
            final Property prop = entry.getKey();
            props.put(prop.getName(), entry.getValue().booleanValue() ?
                config.getStrSubstitutor().replace(prop.getValue()) : prop.getValue());
        }

        final LogEvent result = new Log4jLogEvent.Builder(source).setContextMap(props).build();
        return result;
    }
