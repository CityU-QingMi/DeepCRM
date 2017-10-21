    public static String getRuntimeConfigDefsAsString() {
        
        log.debug("Trying to load runtime config defs file");
        
        try {
            InputStreamReader reader =
                    new InputStreamReader(WebloggerConfig.class.getResourceAsStream(RUNTIME_CONFIG));
            StringWriter configString = new StringWriter();
            
            char[] buf = new char[RollerConstants.EIGHT_KB_IN_BYTES];
            int length = 0;
            while((length = reader.read(buf)) > 0) {
                configString.write(buf, 0, length);
            }
            
            reader.close();
            
            return configString.toString();
        } catch(Exception e) {
            log.error("Error loading runtime config defs file", e);
        }
        
        return "";
    }
