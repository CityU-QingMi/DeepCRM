    @Test
    public void testReentrantRequestSession() throws Exception
    {
        String contextPath = "";
        String servletMapping = "/server";
        
        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        SessionDataStoreFactory storeFactory = new NullSessionDataStoreFactory();
        
        TestServer server = new TestServer(0, -1, 60, cacheFactory, storeFactory);

        
        server.addContext(contextPath).addServlet(TestServlet.class, servletMapping);
        try
        {
            server.start();
            int port = server.getPort();

            HttpClient client = new HttpClient();
            client.start();
            try
            {
                //create the session
                ContentResponse response = client.GET("http://localhost:" + port + contextPath + servletMapping + "?action=create&port=" + port + "&path=" + contextPath + servletMapping);
                assertEquals(HttpServletResponse.SC_OK,response.getStatus());
                
                String sessionCookie = response.getHeaders().get("Set-Cookie");
                assertTrue(sessionCookie != null);
                
                //make a request that will make a simultaneous request for the same session
                Request request = client.newRequest("http://localhost:" + port + contextPath + servletMapping + "?action=reenter&port=" + port + "&path=" + contextPath + servletMapping);
                request.header("Cookie", sessionCookie);
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
