    private PlanetCache() {
        
        cacheEnabled = WebloggerConfig.getBooleanProperty(CACHE_ID + ".enabled");
        
        Map<String, String> cacheProps = new HashMap<String, String>();
        cacheProps.put("id", CACHE_ID);
        Enumeration allProps = WebloggerConfig.keys();
        String prop;
        while(allProps.hasMoreElements()) {
            prop = (String) allProps.nextElement();
            
            // we are only interested in props for this cache
            if (prop.startsWith(CACHE_ID + ".")) {
                cacheProps.put(prop.substring(CACHE_ID.length()+1), 
                        WebloggerConfig.getProperty(prop));
            }
        }
        
        log.info("Planet cache = "+cacheProps);
        
        if (cacheEnabled) {
            contentCache = CacheManager.constructCache(null, cacheProps);
        } else {
            log.warn("Caching has been DISABLED");
        }
        
        // lookup our timeout value
        String timeoutString = WebloggerConfig.getProperty("cache.planet.timeout");
        try {
            long timeoutSecs = Long.parseLong(timeoutString);
            this.timeout = timeoutSecs * RollerConstants.SEC_IN_MS;
        } catch(Exception e) {
            // ignored ... illegal value
        }
    }
