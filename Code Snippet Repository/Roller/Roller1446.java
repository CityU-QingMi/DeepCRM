    public static boolean getBooleanProperty(String name) {
        
        // get the value first, then convert
        String value = PlanetRuntimeConfig.getProperty(name);
        
        if(value == null)
            return false;
        
        return (new Boolean(value)).booleanValue();
    }
