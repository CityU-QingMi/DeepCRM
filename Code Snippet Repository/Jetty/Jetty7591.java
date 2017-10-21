    @Test
    public void testSessionCreateForward () throws Exception
    {
        String contextPath = "";
        String contextB = "/contextB";
        String servletMapping = "/server";
        int inactivePeriod = 20;
        int scavengePeriod = 3;
        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        SessionDataStoreFactory storeFactory = new TestSessionDataStoreFactory();
        
        _server1 = new TestServer(0, inactivePeriod, scavengePeriod, cacheFactory, storeFactory);
        
        ServletHolder holder = new ServletHolder(_servlet);
        ServletContextHandler contextHandler = _server1.addContext(contextPath);
        contextHandler.addServlet(holder, servletMapping);
        ServletContextHandler ctxB = _server1.addContext(contextB);
        ctxB.addServlet(TestServletB.class, servletMapping);
        _server1.start();
        int port1 = _server1.getPort();

        try (StacklessLogging stackless = new StacklessLogging(Log.getLogger("org.eclipse.jetty.server.session")))
        {
            HttpClient client = new HttpClient();
            client.start();
            String url = "http://localhost:" + port1 + contextPath + servletMapping;

            //make a request to set up a session on the server
            ContentResponse response = client.GET(url+"?action=forward");
            assertEquals(HttpServletResponse.SC_OK,response.getStatus());
  
            //ensure work has finished on the server side
            _synchronizer.await();
            
            //check that the sessions exist persisted
            assertTrue(contextHandler.getSessionHandler().getSessionCache().getSessionDataStore().exists(_servlet._id));
            assertTrue(ctxB.getSessionHandler().getSessionCache().getSessionDataStore().exists(_servlet._id));
        }
        finally
        {
            _server1.stop();
        }
    }
