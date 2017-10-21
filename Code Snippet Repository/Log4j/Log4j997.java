    private void loadKnownTypeConverters(final Collection<PluginType<?>> knownTypes) {
        for (final PluginType<?> knownType : knownTypes) {
            final Class<?> clazz = knownType.getPluginClass();
            if (TypeConverter.class.isAssignableFrom(clazz)) {
                @SuppressWarnings("rawtypes")
                final Class<? extends TypeConverter> pluginClass =  clazz.asSubclass(TypeConverter.class);
                final Type conversionType = getTypeConverterSupportedType(pluginClass);
                final TypeConverter<?> converter = ReflectionUtil.instantiate(pluginClass);
                if (registry.putIfAbsent(conversionType, converter) != null) {
                    LOGGER.warn("Found a TypeConverter [{}] for type [{}] that already exists.", converter,
                        conversionType);
                }
            }
        }
    }
