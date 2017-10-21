    private void loadPagePluginClasses() {
        log.debug("Initializing page plugins");
        
        String pluginStr = WebloggerConfig.getProperty("plugins.page");
        if (log.isDebugEnabled()) {
            log.debug(pluginStr);
        }
        if (pluginStr != null) {
            String[] plugins = StringUtils.stripAll(
                    StringUtils.split(pluginStr, ",") );
            for (String plugin : plugins) {
                if (log.isDebugEnabled()) {
                    log.debug("try " + plugin);
                }
                try {
                    Class pluginClass = Class.forName(plugin);
                    if (isPagePlugin(pluginClass)) {
                        WeblogEntryPlugin weblogEntryPlugin = (WeblogEntryPlugin)pluginClass.newInstance();
                        mPagePlugins.put(weblogEntryPlugin.getName(), pluginClass);
                    } else {
                        log.warn(pluginClass + " is not a PagePlugin");
                    }
                } catch (ClassNotFoundException e) {
                    log.error("ClassNotFoundException for " + plugin);
                } catch (InstantiationException e) {
                    log.error("InstantiationException for " + plugin);
                } catch (IllegalAccessException e) {
                    log.error("IllegalAccessException for " + plugin);
                }
            }
        }
    }
