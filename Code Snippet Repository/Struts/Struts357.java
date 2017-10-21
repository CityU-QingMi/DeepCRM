    protected void reloadBundles(Map<String, Object> context) {
        if (reloadBundles) {
            try {
                Boolean reloaded;
                if (context != null) {
                    reloaded = (Boolean) ObjectUtils.defaultIfNull(context.get(RELOADED), Boolean.FALSE);
                } else {
                    reloaded = Boolean.FALSE;
                }
                if (!reloaded) {
                    bundlesMap.clear();
                    try {
                        clearMap(ResourceBundle.class, null, "cacheList");
                    } catch (NoSuchFieldException e) {
                        // happens in IBM JVM, that has a different ResourceBundle impl
                        // it has a 'cache' member
                        clearMap(ResourceBundle.class, null, "cache");
                    }

                    // now, for the true and utter hack, if we're running in tomcat, clear
                    // it's class loader resource cache as well.
                    clearTomcatCache();
                    if (context != null) {
                        context.put(RELOADED, true);
                    }
                    LOG.debug("Resource bundles reloaded");
                }
            } catch (Exception e) {
                LOG.error("Could not reload resource bundles", e);
            }
        }
    }
