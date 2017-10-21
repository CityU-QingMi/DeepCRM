    public List<WeblogEntryPlugin> getEntryPlugins() {
        List<WeblogEntryPlugin> availablePlugins = Collections.emptyList();
        try {
            PluginManager ppmgr = WebloggerFactory.getWeblogger()
                    .getPluginManager();
            Map<String, WeblogEntryPlugin> plugins = ppmgr
                    .getWeblogEntryPlugins(getActionWeblog());

            if (plugins.size() > 0) {
                availablePlugins = new ArrayList<WeblogEntryPlugin>();
                for (WeblogEntryPlugin plugin : plugins.values()) {
                    availablePlugins.add(plugin);
                }
            }
        } catch (Exception ex) {
            log.error("Error getting plugins list", ex);
        }
        return availablePlugins;
    }
