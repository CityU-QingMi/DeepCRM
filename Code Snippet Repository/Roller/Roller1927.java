    public static void main(String[] args) throws Exception {
        
        // before we can do anything we need to bootstrap the planet backend
        WebloggerStartup.prepare();
        
        // we need to use our own planet provider for integration
        String guiceModule = WebloggerConfig.getProperty("planet.aggregator.guice.module");
        WebloggerProvider provider = new GuiceWebloggerProvider(guiceModule);
        WebloggerFactory.bootstrap(provider);
        
        SyncWebsitesTask task = new SyncWebsitesTask();
        task.init();
        task.run();
    }
