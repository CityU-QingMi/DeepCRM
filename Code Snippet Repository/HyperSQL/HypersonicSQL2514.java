    public static int getIntegerProperty(Properties props, String key,
                                         int defaultValue) {

        String prop = props.getProperty(key);

        try {
            if (prop != null) {
                prop         = prop.trim();
                defaultValue = Integer.parseInt(prop);
            }
        } catch (NumberFormatException e) {}

        return defaultValue;
    }
