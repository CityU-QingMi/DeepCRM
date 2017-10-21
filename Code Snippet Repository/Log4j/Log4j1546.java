    public static Object instantiateByKey(final Properties props, final String key, final Class<?> superClass,
                                   final Object defaultValue) {

        // Get the value of the property in string form
        final String className = findAndSubst(key, props);
        if (className == null) {
            LOGGER.error("Could not find value for key {}", key);
            return defaultValue;
        }
        // Trim className to avoid trailing spaces that cause problems.
        return OptionConverter.instantiateByClassName(className.trim(), superClass,
            defaultValue);
    }
