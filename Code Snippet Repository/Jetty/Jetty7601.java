    @Test
    public void testSessionRedirect() throws Exception
    {

        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        SessionDataStoreFactory storeFactory = new TestSessionDataStoreFactory();
        
        TestServer testServer = new TestServer(0, -1, -1, cacheFactory,storeFactory);
        ServletContextHandler testServletContextHandler = testServer.addContext("/context");
        testServletContextHandler.addServlet(Servlet1.class, "/one");
        testServletContextHandler.addServlet(Servlet2.class, "/two");

       
      

        try
        {
            testServer.start();
            int serverPort=testServer.getPort();
            HttpClient client = new HttpClient();
            client.setFollowRedirects(true); //ensure client handles redirects
            client.start();
            try
            {
                //make a request to the first servlet, which will redirect
                ContentResponse response = client.GET("http://localhost:" + serverPort + "/context/one");
                assertEquals(HttpServletResponse.SC_OK, response.getStatus());
            }
            finally
            {
                client.stop();
            }
        }
        finally
        {
            testServer.stop();
        }
        
    }
