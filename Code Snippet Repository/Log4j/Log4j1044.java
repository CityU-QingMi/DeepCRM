    private AppenderComponentBuilder createAppender(final String key, final Properties properties) {
        final String name = (String) properties.remove(CONFIG_NAME);
        if (Strings.isEmpty(name)) {
            throw new ConfigurationException("No name attribute provided for Appender " + key);
        }
        final String type = (String) properties.remove(CONFIG_TYPE);
        if (Strings.isEmpty(type)) {
            throw new ConfigurationException("No type attribute provided for Appender " + key);
        }
        final AppenderComponentBuilder appenderBuilder = builder.newAppender(name, type);
        addFiltersToComponent(appenderBuilder, properties);
        final Properties layoutProps = PropertiesUtil.extractSubset(properties, "layout");
        if (layoutProps.size() > 0) {
            appenderBuilder.add(createLayout(name, layoutProps));
        }

        return processRemainingProperties(appenderBuilder, properties);
    }
