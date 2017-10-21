    public static int getIntProperty(String name) {
        
        // get the value first, then convert
        String value = WebloggerRuntimeConfig.getProperty(name);
        
        if (value == null) {
            return -1;
        }
        
        int intval = -1;
        try {
            intval = Integer.parseInt(value);
        } catch(Exception e) {
            log.warn("Trouble converting to int: "+name, e);
        }
        
        return intval;
    }
