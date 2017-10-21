    @Test
    public void testDeleteUnloadableSession () throws Exception
    {        
        String contextPath = "";
        String servletMapping = "/server";
        int inactivePeriod = -1;
        int scavengePeriod = 100;
        
        DefaultSessionCacheFactory cacheFactory = new DefaultSessionCacheFactory();
        cacheFactory.setEvictionPolicy(SessionCache.NEVER_EVICT);
        cacheFactory.setRemoveUnloadableSessions(true);
        SessionDataStoreFactory storeFactory = new DelSessionDataStoreFactory();
        ((AbstractSessionDataStoreFactory)storeFactory).setGracePeriodSec(scavengePeriod);
        
        TestServer server = new TestServer(0, inactivePeriod, scavengePeriod, cacheFactory, storeFactory);
        ServletContextHandler context = server.addContext(contextPath);
        
        TestServlet servlet = new TestServlet();
        ServletHolder holder = new ServletHolder(servlet);
        context.addServlet(holder, servletMapping);
    
        try (StacklessLogging stackless = new StacklessLogging(Log.getLogger("org.eclipse.jetty.server.session")))
        {
            server.start();
            int port = server.getPort();          
            HttpClient client = new HttpClient();
            client.start();
            try
            {
                String sessionCookie = "JSESSIONID=w0rm3zxpa6h1zg1mevtv76b3te00.w0;$Path=/";
                Request request = client.newRequest("http://localhost:" + port + contextPath + servletMapping+ "?action=test");
                request.header("Cookie", sessionCookie);
                ContentResponse response = request.send();
                assertEquals(HttpServletResponse.SC_OK,response.getStatus());
                
                assertFalse(context.getSessionHandler().getSessionCache().getSessionDataStore().exists(TestServer.extractSessionId(sessionCookie)));

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
