    @com.google.inject.Inject  
    protected JPAPlanetImpl(
            JPAPersistenceStrategy strategy, 
            PlanetManager     planetManager, 
            PropertiesManager propertiesManager,
            PlanetURLStrategy       urlStrategy,
            FeedFetcher       feedFetcher) throws RollerException {
        
        this.strategy = strategy;
        this.propertiesManager = propertiesManager;
        this.planetManager = planetManager;
        this.urlStrategy = urlStrategy;
        this.feedFetcher = feedFetcher;
    }
