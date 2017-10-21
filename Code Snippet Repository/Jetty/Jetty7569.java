    @Test
    public void testNoExpireSessionInUse() throws Exception
    {
        int maxInactive = 3;
        int sleep = maxInactive + (int)(maxInactive * 0.8);
        
        
        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        SessionDataStoreFactory storeFactory = createSessionDataStoreFactory();
        ((AbstractSessionDataStoreFactory)storeFactory).setGracePeriodSec(TestServer.DEFAULT_SCAVENGE_SEC);
        
        TestServer server = new TestServer(0, maxInactive,  1, cacheFactory, storeFactory);
        ServletContextHandler ctxA = server.addContext("/mod");
        ctxA.addServlet(TestModServlet.class, "/test");

        server.start();
        int port=server.getPort();
        try
        {
            HttpClient client = new HttpClient();
            client.start();
            try
            {
                // Perform a request to create a session

                ContentResponse response = client.GET("http://localhost:" + port + "/mod/test?action=create");

                assertEquals(HttpServletResponse.SC_OK,response.getStatus());
                String sessionCookie = response.getHeaders().get("Set-Cookie");
                assertTrue(sessionCookie != null);

                //do another request that will sleep long enough for the session expiry time to have passed
                //before trying to access the session and ensure it is still there
                Request request = client.newRequest("http://localhost:" + port + "/mod/test?action=sleep&val="+sleep);
                response = request.send();

                assertEquals(HttpServletResponse.SC_OK,response.getStatus());             
                
            }
            finally
            {
                client.stop();
            }
        }
        finally
        {
            server.stop();
        }
    }
