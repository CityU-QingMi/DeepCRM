    @Test
    public void testSessionRenewalDefaultCache() throws Exception
    {
        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        SessionDataStoreFactory storeFactory = new TestSessionDataStoreFactory();
        
        _server = new TestServer(0, -1, -1, cacheFactory, storeFactory);
        
        doTest(new RenewalVerifier() {

            @Override
            public void verify(WebAppContext context, String oldSessionId, String newSessionId)
            throws Exception
            {
                //verify the contents of the cache changed
                assertTrue(context.getSessionHandler().getSessionCache().contains(newSessionId));
                assertFalse(context.getSessionHandler().getSessionCache().contains(oldSessionId));
                super.verify(context, oldSessionId, newSessionId);
            }
            
        });
    }
