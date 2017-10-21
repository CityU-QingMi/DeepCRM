    @Override
    public void initialize() throws Exception {
        
        log.info("Initializing Roller Planet business tier");
        
        getPropertiesManager().initialize();
        getWebloggerManager().initialize();
        
        // we always need to do a flush after initialization because it's
        // possible that some changes need to be persisted
        try {
            flush();
        } catch(RollerException ex) {
            throw new InitializationException("Error flushing after initialization", ex);
        }
        
        log.info("Roller Planet business tier successfully initialized");
    }
