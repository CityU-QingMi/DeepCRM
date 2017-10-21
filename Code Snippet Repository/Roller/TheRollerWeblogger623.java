    private SiteWideCache() {
        
        cacheEnabled = WebloggerConfig.getBooleanProperty(CACHE_ID+".enabled");
        
        Map<String, String> cacheProps = new HashMap<String, String>();
        cacheProps.put("id", CACHE_ID);
        Enumeration allProps = WebloggerConfig.keys();
        String prop = null;
        while(allProps.hasMoreElements()) {
            prop = (String) allProps.nextElement();
            
            // we are only interested in props for this cache
            if(prop.startsWith(CACHE_ID+".")) {
                cacheProps.put(prop.substring(CACHE_ID.length()+1), 
                        WebloggerConfig.getProperty(prop));
            }
        }
        
        log.info(cacheProps);
        
        if(cacheEnabled) {
            contentCache = CacheManager.constructCache(this, cacheProps);
        } else {
            log.warn("Caching has been DISABLED");
        }
    }
