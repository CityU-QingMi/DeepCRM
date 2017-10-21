    public PatternParser(final Configuration config, final String converterKey, final Class<?> expectedClass,
            final Class<?> filterClass) {
        this.config = config;
        final PluginManager manager = new PluginManager(converterKey);
        manager.collectPlugins(config == null ? null : config.getPluginPackages());
        final Map<String, PluginType<?>> plugins = manager.getPlugins();
        final Map<String, Class<PatternConverter>> converters = new LinkedHashMap<>();

        for (final PluginType<?> type : plugins.values()) {
            try {
                @SuppressWarnings("unchecked")
                final Class<PatternConverter> clazz = (Class<PatternConverter>) type.getPluginClass();
                if (filterClass != null && !filterClass.isAssignableFrom(clazz)) {
                    continue;
                }
                final ConverterKeys keys = clazz.getAnnotation(ConverterKeys.class);
                if (keys != null) {
                    for (final String key : keys.value()) {
                        if (converters.containsKey(key)) {
                            LOGGER.warn("Converter key '{}' is already mapped to '{}'. " +
                                    "Sorry, Dave, I can't let you do that! Ignoring plugin [{}].",
                                key, converters.get(key), clazz);
                        } else {
                            converters.put(key, clazz);
                        }
                    }
                }
            } catch (final Exception ex) {
                LOGGER.error("Error processing plugin " + type.getElementName(), ex);
            }
        }
        converterRules = converters;
    }
