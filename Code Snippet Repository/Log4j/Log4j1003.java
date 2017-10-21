    @SuppressWarnings("")
    private static <T> T parseDefaultValue(final TypeConverter<T> converter, final Object defaultValue) {
        if (defaultValue == null) {
            return null;
        }
        if (!(defaultValue instanceof String)) {
            return (T) defaultValue;
        }
        try {
            return converter.convert((String) defaultValue);
        } catch (final Exception e) {
            LOGGER.debug("Can't parse default value [{}] for type [{}].", defaultValue, converter.getClass(), e);
            return null;
        }
    }
