    @Test
    public void testSessionRenewalNullCache() throws Exception
    {
        SessionCacheFactory cacheFactory = new NullSessionCacheFactory();
        SessionDataStoreFactory storeFactory = new TestSessionDataStoreFactory();
        
        //make the server with a NullSessionCache
        _server = new TestServer(0, -1, -1, cacheFactory, storeFactory);
        doTest(new RenewalVerifier());
    }
