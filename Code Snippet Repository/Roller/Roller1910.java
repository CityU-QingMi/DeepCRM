    public static boolean getBooleanProperty(String name) {
        
        // get the value first, then convert
        String value = WebloggerRuntimeConfig.getProperty(name);
        
        if (value == null) {
            return false;
        }

        return Boolean.valueOf(value);
    }
