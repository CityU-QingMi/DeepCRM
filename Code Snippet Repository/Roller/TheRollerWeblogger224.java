    public void configure(Binder binder) {

        binder.bind(Weblogger.class).to(JPAWebloggerImpl.class);
        
        binder.bind(JPAPersistenceStrategy.class);       
        
        binder.bind(AutoPingManager.class).to(     JPAAutoPingManagerImpl.class);   
        binder.bind(BookmarkManager.class).to(     JPABookmarkManagerImpl.class);  
        binder.bind(PingQueueManager.class).to(    JPAPingQueueManagerImpl.class);   
        binder.bind(PingTargetManager.class).to(   JPAPingTargetManagerImpl.class); 
        binder.bind(PropertiesManager.class).to(   JPAPropertiesManagerImpl.class);   
        binder.bind(ThreadManager.class).to(       JPAThreadManagerImpl.class);
        binder.bind(UserManager.class).to(         JPAUserManagerImpl.class);   
        binder.bind(WeblogManager.class).to(       JPAWeblogManagerImpl.class);   
        binder.bind(WeblogEntryManager.class).to(  JPAWeblogEntryManagerImpl.class);   
        binder.bind(OAuthManager.class).to(        JPAOAuthManagerImpl.class);

        binder.bind(OAuthValidator.class).to(      SimpleOAuthValidator.class);
                
        binder.bind(MediaFileManager.class).to(    JPAMediaFileManagerImpl.class);
        binder.bind(FileContentManager.class).to(  FileContentManagerImpl.class);
        binder.bind(IndexManager.class).to(        IndexManagerImpl.class);
        binder.bind(PluginManager.class).to(       PluginManagerImpl.class);    
        binder.bind(ThemeManager.class).to(        ThemeManagerImpl.class);
        
        binder.bind(URLStrategy.class).to(         MultiWeblogURLStrategy.class);
        binder.bind(PlanetURLStrategy.class).to(   MultiPlanetURLStrategy.class);
		binder.bind(Planet.class).to(              JPAPlanetImpl.class);
        binder.bind(PlanetManager.class).to(       JPAPlanetManagerImpl.class);   
        binder.bind(FeedFetcher.class).to(         WebloggerRomeFeedFetcher.class);
    }
