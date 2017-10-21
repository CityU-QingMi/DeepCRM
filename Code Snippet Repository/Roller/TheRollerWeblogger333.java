    @Override
    public void myPrepare() {
        try {
            // just grab our properties map
            PropertiesManager pMgr = WebloggerFactory.getWeblogger().getPropertiesManager();
            setProperties(pMgr.getProperties());

        } catch (RollerException ex) {
            log.error("Error loading planet properties");
        }
        
        // set config def used to draw the view
        RuntimeConfigDefs defs = PlanetRuntimeConfig.getRuntimeConfigDefs();

        List<ConfigDef> configDefs = defs.getConfigDefs();
        for (ConfigDef configDef : configDefs) {
            if("global-properties".equals(configDef.getName())) {
                setGlobalConfigDef(configDef);
            }
        }
    }
