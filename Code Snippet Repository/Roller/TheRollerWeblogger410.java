    private String render(String str) {
        String ret = str;
        mLogger.debug("Applying page plugins to string");
        Map<String, WeblogEntryPlugin> inPlugins = getWebsite().getInitializedPlugins();
        if (str != null && inPlugins != null) {
            List entryPlugins = getPluginsList();
            
            // if no Entry plugins, don't bother looping.
            if (entryPlugins != null && !entryPlugins.isEmpty()) {
                
                // now loop over mPagePlugins, matching
                // against Entry plugins (by name):
                // where a match is found render Plugin.
                for (Map.Entry<String, WeblogEntryPlugin> entry : inPlugins.entrySet()) {
                    if (entryPlugins.contains(entry.getKey())) {
                        WeblogEntryPlugin pagePlugin = entry.getValue();
                        try {
                            ret = pagePlugin.render(this, ret);
                        } catch (Exception e) {
                            mLogger.error("ERROR from plugin: " + pagePlugin.getName(), e);
                        }
                    }
                }
            }
        } 
        return HTMLSanitizer.conditionallySanitize(ret);
    }
