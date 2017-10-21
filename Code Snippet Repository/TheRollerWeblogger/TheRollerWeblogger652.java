    @Override
    public void myPrepare() {
        try {
            // just grab our properties map and make it available to the action
            PropertiesManager mgr = WebloggerFactory.getWeblogger().getPropertiesManager();
            setProperties(mgr.getProperties());
        } catch (WebloggerException ex) {
            log.error("Error getting runtime properties map", ex);
            addError("Unexpected error accessing Roller properties");
        }
        
        try {
            WeblogManager mgr =  WebloggerFactory.getWeblogger().getWeblogManager();
            setWeblogs(mgr.getWeblogs(true, null, null, null, 0, -1));
        } catch (WebloggerException ex) {
            log.error("Error getting weblogs", ex);
            addError("frontpageConfig.weblogs.error");
        }

        // set config def used to draw the view
        RuntimeConfigDefs defs = WebloggerRuntimeConfig.getRuntimeConfigDefs();
        List<ConfigDef> configDefs = defs.getConfigDefs();
        for (ConfigDef configDef : configDefs) {
            if ("global-properties".equals(configDef.getName())) {
                setGlobalConfigDef(configDef);
            }
        }
        
        // load plugins list
        PluginManager pmgr = WebloggerFactory.getWeblogger().getPluginManager();
        setPluginsList(pmgr.getCommentPlugins());
    }
