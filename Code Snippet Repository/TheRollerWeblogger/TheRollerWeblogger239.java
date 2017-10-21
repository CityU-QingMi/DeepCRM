    private void loadCommentPlugins() {
        
        log.debug("Initializing comment plugins");
        
        String pluginStr = WebloggerConfig.getProperty("comment.formatter.classnames");
        if (pluginStr != null) {
            String[] plugins = StringUtils.stripAll(StringUtils.split(pluginStr, ","));
            for (int i=0; i < plugins.length; i++) {
                log.debug("trying " + plugins[i]);
                
                try {
                    Class pluginClass = Class.forName(plugins[i]);
                    WeblogEntryCommentPlugin plugin = 
                            (WeblogEntryCommentPlugin) pluginClass.newInstance();
                    
                    // make sure and maintain ordering
                    commentPlugins.add(i, plugin);
                    
                    log.debug("Configured comment plugin: "+plugins[i]);
                    
                } catch (ClassCastException e) {
                    log.error("ClassCastException for " + plugins[i]);
                } catch (ClassNotFoundException e) {
                    log.error("ClassNotFoundException for " + plugins[i]);
                } catch (InstantiationException e) {
                    log.error("InstantiationException for " + plugins[i]);
                } catch (IllegalAccessException e) {
                    log.error("IllegalAccessException for " + plugins[i]);
                }
            }
        }
        
    }
