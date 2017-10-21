    public static RuntimeConfigDefs getRuntimeConfigDefs() {
        
        if(configDefs == null) {
            
            // unmarshall the config defs file
            try {
                InputStream is = 
                        PlanetRuntimeConfig.class.getResourceAsStream(runtime_config);
                
                RuntimeConfigDefsParser parser = new RuntimeConfigDefsParser();
                configDefs = parser.unmarshall(is);
                
            } catch(Exception e) {
                // error while parsing :(
                log.error("Error parsing runtime config defs", e);
            }
            
        }
        
        return configDefs;
    }
