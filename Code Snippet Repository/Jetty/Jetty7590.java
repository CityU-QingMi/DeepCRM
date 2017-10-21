    @Test
    public void testSessionCreateAndInvalidateWithSave() throws Exception
    {
        String contextPath = "";
        String servletMapping = "/server";
        int inactivePeriod = 20;
        int scavengePeriod = 3;
        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        cacheFactory.setSaveOnCreate(true);
        SessionDataStoreFactory storeFactory = new TestSessionDataStoreFactory();
        _server1 = new TestServer(0, inactivePeriod, scavengePeriod, cacheFactory, storeFactory);    
        ServletHolder holder = new ServletHolder(_servlet);
        ServletContextHandler contextHandler = _server1.addContext(contextPath);
        contextHandler.addServlet(holder, servletMapping);
        _servlet.setStore(contextHandler.getSessionHandler().getSessionCache().getSessionDataStore());
        _server1.start();
        int port1 = _server1.getPort();

        try (StacklessLogging stackless = new StacklessLogging(Log.getLogger("org.eclipse.jetty.server.session")))
        {
            HttpClient client = new HttpClient();
            client.start();
            String url = "http://localhost:" + port1 + contextPath + servletMapping+"?action=createinv&check=true";

            //make a request to set up a session on the server
            ContentResponse response = client.GET(url);
            assertEquals(HttpServletResponse.SC_OK,response.getStatus());
            
            //check that the session does not exist
           assertFalse(contextHandler.getSessionHandler().getSessionCache().getSessionDataStore().exists(_servlet._id));
        }
        finally
        {
            _server1.stop();
        }
    }
