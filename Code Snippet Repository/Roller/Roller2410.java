    public void myPrepare() {
        
        try {
            WeblogEntryManager wmgr = WebloggerFactory.getWeblogger().getWeblogEntryManager();
            
            // set categories list
            setWeblogCategories(wmgr.getWeblogCategories(getActionWeblog()));
            
            // set the Editor Page list
            UIPluginManager pmgr = RollerContext.getUIPluginManager();
            List editorList = pmgr.getWeblogEntryEditors();
            if(editorList != null) {
                setEditorsList(editorList);
            }
            
            // set plugins list
            PluginManager ppmgr = WebloggerFactory.getWeblogger().getPluginManager();
            Map<String, WeblogEntryPlugin> pluginsMap = ppmgr.getWeblogEntryPlugins(getActionWeblog());
            List<WeblogEntryPlugin> plugins = new ArrayList<WeblogEntryPlugin>();
            for (WeblogEntryPlugin entryPlugin : pluginsMap.values()) {
                plugins.add(entryPlugin);
            }

            // sort
            setPluginsList(plugins);

        } catch (Exception ex) {
            log.error("Error preparing weblog config action", ex);
        }
    }
