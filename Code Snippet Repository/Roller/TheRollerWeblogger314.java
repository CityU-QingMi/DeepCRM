    public static boolean getBooleanProperty(String name, boolean defaultValue) {
        // get the value first, then convert
        String value = WebloggerConfig.getProperty(name);

        if(value == null) {
            return defaultValue;
        }

        return Boolean.valueOf(value);
    }
