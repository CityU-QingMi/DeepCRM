    public static String getProperty(String name) {
        
        String value = null;
        
        try {
            PropertiesManager pmgr = WebloggerFactory.getWeblogger().getPropertiesManager();
            RuntimeConfigProperty prop = pmgr.getProperty(name);
            if(prop != null) {
                value = prop.getValue();
            }
        } catch(Exception e) {
            log.warn("Trouble accessing property: "+name, e);
        }
        
        log.debug("fetched property ["+name+"="+value+"]");

        return value;
    }
