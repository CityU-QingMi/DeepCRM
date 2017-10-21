    public void doTest(RenewalVerifier verifier) throws Exception
    {
        String contextPath = "";
        String servletMapping = "/server";
        WebAppContext context = _server.addWebAppContext(".", contextPath);
        context.setParentLoaderPriority(true);
        context.addServlet(TestServlet.class, servletMapping);
        TestHttpSessionIdListener testListener = new TestHttpSessionIdListener();
        context.addEventListener(testListener);
        


        HttpClient client = new HttpClient();
        try
        {
            _server.start();
            int port=_server.getPort();
            
            client.start();

            //make a request to create a session
            ContentResponse response = client.GET("http://localhost:" + port + contextPath + servletMapping + "?action=create");
            assertEquals(HttpServletResponse.SC_OK,response.getStatus());

            String sessionCookie = response.getHeaders().get("Set-Cookie");
            assertTrue(sessionCookie != null);
            assertFalse(testListener.isCalled());

            //make a request to change the sessionid
            Request request = client.newRequest("http://localhost:" + port + contextPath + servletMapping + "?action=renew");
            ContentResponse renewResponse = request.send();

            assertEquals(HttpServletResponse.SC_OK,renewResponse.getStatus());
            String renewSessionCookie = renewResponse.getHeaders().get("Set-Cookie");
            assertNotNull(renewSessionCookie);
            assertNotSame(sessionCookie, renewSessionCookie);
            assertTrue(testListener.isCalled());

            if (verifier != null)
                verifier.verify(context, TestServer.extractSessionId(sessionCookie), TestServer.extractSessionId(renewSessionCookie));
        }
        finally
        {
            client.stop();
            _server.stop();
        }
    }
