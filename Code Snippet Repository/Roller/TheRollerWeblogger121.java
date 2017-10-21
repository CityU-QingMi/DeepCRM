    public static void bootstrap(WebloggerProvider provider)
            throws BootstrapException {
        
        // if the app hasn't been properly started so far then bail
        if (!WebloggerStartup.isPrepared()) {
            throw new IllegalStateException("Cannot bootstrap until application has been properly prepared");
        }
        
        if (provider == null) {
            throw new NullPointerException("WebloggerProvider is null");
        }
        
        LOG.info("Bootstrapping Roller Weblogger business tier");
        
        LOG.info("Weblogger Provider = " + provider.getClass().getName());
        
        // save reference to provider
        webloggerProvider = provider;
        
        // bootstrap weblogger provider
        webloggerProvider.bootstrap();
        
        // make sure we are all set
        if(webloggerProvider.getWeblogger() == null) {
            throw new BootstrapException("Bootstrapping failed, Weblogger instance is null");
        }
        
        LOG.info("Roller Weblogger business tier successfully bootstrapped");
        LOG.info("   Version: " + webloggerProvider.getWeblogger().getVersion());
        LOG.info("   Revision: " + webloggerProvider.getWeblogger().getRevision());
    }
