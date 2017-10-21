    private static <B extends ComponentBuilder<B>> ComponentBuilder<B> createComponent(final ComponentBuilder<?> parent,
                                                                                       final String key,
                                                                                       final Properties properties) {
        final String name = (String) properties.remove(CONFIG_NAME);
        final String type = (String) properties.remove(CONFIG_TYPE);
        if (Strings.isEmpty(type)) {
            throw new ConfigurationException("No type attribute provided for component " + key);
        }
        final ComponentBuilder<B> componentBuilder = parent.getBuilder().newComponent(name, type);
        return processRemainingProperties(componentBuilder, properties);
    }
