    @com.google.inject.Inject
    protected JPAWebloggerImpl(
        JPAPersistenceStrategy strategy,
        AutoPingManager      autoPingManager,
        BookmarkManager      bookmarkManager,
        IndexManager         indexManager,
        MediaFileManager     mediaFileManager,
        FileContentManager   fileContentManager,
        PingQueueManager     pingQueueManager,
        PingTargetManager    pingTargetManager,
        PluginManager        pluginManager,
        PropertiesManager    propertiesManager,
        ThemeManager         themeManager,
        ThreadManager        threadManager,
        UserManager          userManager,
        WeblogManager        weblogManager,
        WeblogEntryManager   weblogEntryManager,
        OAuthManager         oauthManager,
		FeedFetcher          feedFetcher,
        PlanetManager        planetManager,
        PlanetURLStrategy    planetUrlStrategy,
        URLStrategy          urlStrategy) throws WebloggerException {
        
        super(
            autoPingManager,
            bookmarkManager,
            indexManager,
            mediaFileManager,
            fileContentManager,
            pingQueueManager,
            pingTargetManager,
            pluginManager,
            propertiesManager,
            themeManager,
            threadManager,
            userManager,
            weblogManager,
            weblogEntryManager,
            oauthManager,
            feedFetcher,
            planetManager,
            planetUrlStrategy,
            urlStrategy);
        
        this.strategy = strategy;
    }
