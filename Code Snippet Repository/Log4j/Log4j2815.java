    @Override
    protected void before() throws Throwable {
        final Map<String, String> configMap = new HashMap<>(2);
        // Cleans framework before first init. Subsequent init invocations do not clean framework.
        configMap.put("org.osgi.framework.storage.clean", "onFirstInit");
        configMap.put("felix.log.level", "4");
        configMap.put("eclipse.log.level", "ALL");
        // Hack to get the build working on Windows. Could try newer versions of Felix.
        configMap.put("felix.cache.locking", "false");        
        // Delegates loading of endorsed libraries to JVM classloader
        // config.put("org.osgi.framework.bootdelegation", "javax.*,org.w3c.*,org.xml.*");
        framework = factory.newFramework(configMap);
        framework.init();
        framework.start();
    }
