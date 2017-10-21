    private static <B extends ComponentBuilder<?>> B processRemainingProperties(final B builder,
                                                                                final Properties properties) {
        while (properties.size() > 0) {
            final String propertyName = properties.stringPropertyNames().iterator().next();
            final int index = propertyName.indexOf('.');
            if (index > 0) {
                final String prefix = propertyName.substring(0, index);
                final Properties componentProperties = PropertiesUtil.extractSubset(properties, prefix);
                builder.addComponent(createComponent(builder, prefix, componentProperties));
            } else {
                builder.addAttribute(propertyName, properties.getProperty(propertyName));
                properties.remove(propertyName);
            }
        }
        return builder;
    }
