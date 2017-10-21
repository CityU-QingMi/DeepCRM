    public static Cache constructCache(CacheHandler handler, Map properties) {
        
        log.debug("Constructing new cache with props "+properties);
        
        Cache cache = null;
        
        if(properties != null && properties.containsKey("factory")) {
            // someone wants a custom cache instance
            String classname = (String) properties.get("factory");
            
            try {
                // use reflection to instantiate the factory class
                Class factoryClass = Class.forName(classname);
                CacheFactory factory = (CacheFactory) factoryClass.newInstance();
                
                // now ask for a new cache
                cache = factory.constructCache(properties);
            } catch(ClassCastException cce) {
                log.error("It appears that your factory ["+classname+
                        "] does not implement the CacheFactory interface",cce);
            } catch(Exception e) {
                log.error("Unable to instantiate cache factory ["+classname+
                        "] falling back on default", e);
            }
        }
        
        if(cache == null) {
            // ask our default cache factory for a new cache instance
            cache = cacheFactory.constructCache(properties);
        }
        
        if(cache != null) {
            caches.put(cache.getId(), cache);
            
            // register the handler for this new cache
            if(handler != null) {
                cacheHandlers.add(handler);
            }
        }

        return cache;
    }
