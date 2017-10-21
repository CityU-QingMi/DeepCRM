    public static SessionDataStoreFactory newSessionDataStoreFactory()
    {
        MockDataStoreFactory storeFactory = new MockDataStoreFactory();
        MemcachedSessionDataMapFactory mapFactory = new MemcachedSessionDataMapFactory();
        mapFactory.setAddresses(new InetSocketAddress("localhost", 11211));
        
        CachingSessionDataStoreFactory factory = new CachingSessionDataStoreFactory();
        factory.setSessionDataMapFactory(mapFactory);
        factory.setSessionStoreFactory(storeFactory);
        return factory;
    }
