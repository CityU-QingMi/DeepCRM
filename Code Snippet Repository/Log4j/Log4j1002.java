    public static <T> T convert(final String s, final Class<? extends T> clazz, final Object defaultValue) {
        @SuppressWarnings("unchecked")
        final TypeConverter<T> converter = (TypeConverter<T>) TypeConverterRegistry.getInstance().findCompatibleConverter(clazz);
        if (s == null) {
            // don't debug print here, resulting output is hard to understand
            // LOGGER.debug("Null string given to convert. Using default [{}].", defaultValue);
            return parseDefaultValue(converter, defaultValue);
        }
        try {
            return converter.convert(s);
        } catch (final Exception e) {
            LOGGER.warn("Error while converting string [{}] to type [{}]. Using default value [{}].", s, clazz,
                    defaultValue, e);
            return parseDefaultValue(converter, defaultValue);
        }
    }
