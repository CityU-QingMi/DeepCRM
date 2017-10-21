    public boolean isPropertyTrue(String key, boolean defaultValue) {

        String value = stringProps.getProperty(key);

        if (value == null) {
            return defaultValue;
        }

        value = value.trim();

        return value.toLowerCase().equals("true");
    }
