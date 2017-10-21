    public Map<String, WeblogEntryPlugin> getWeblogEntryPlugins(Weblog website) {
        Map<String, WeblogEntryPlugin> ret = new LinkedHashMap<String, WeblogEntryPlugin>();
        for (Class pluginClass : PluginManagerImpl.mPagePlugins.values()) {
            try {
                WeblogEntryPlugin plugin = (WeblogEntryPlugin)pluginClass.newInstance();
                plugin.init(website);
                ret.put(plugin.getName(), plugin);
            } catch (Exception e) {
                log.error("Unable to init() PagePlugin: ", e);
            }
        }
        return ret;
    }
