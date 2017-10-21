    public String applyWeblogEntryPlugins(Map pagePlugins, WeblogEntry entry, String str) {

        String ret = str;
        WeblogEntry copy = new WeblogEntry(entry);
        List<String> entryPlugins = copy.getPluginsList();

        if (entryPlugins != null) {
            for (String key : entryPlugins) {
                WeblogEntryPlugin pagePlugin = (WeblogEntryPlugin) pagePlugins.get(key);
                if (pagePlugin != null) {
                    ret = pagePlugin.render(entry, ret);
                } else {
                    log.error("ERROR: plugin not found: " + key);
                }
            }
        }

        return HTMLSanitizer.conditionallySanitize(ret);
    }
