    private FilterComponentBuilder createFilter(final String key, final Properties properties) {
        final String type = (String) properties.remove(CONFIG_TYPE);
        if (Strings.isEmpty(type)) {
            throw new ConfigurationException("No type attribute provided for Appender " + key);
        }
        final String onMatch = (String) properties.remove("onMatch");
        final String onMisMatch = (String) properties.remove("onMisMatch");
        final FilterComponentBuilder filterBuilder = builder.newFilter(type, onMatch, onMisMatch);
        return processRemainingProperties(filterBuilder, properties);
    }
