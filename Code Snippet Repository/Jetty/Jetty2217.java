    @Test
    public void testIndex() throws Exception
    {        
        // TestOSGiUtil.debugBundles(bundleContext);
        HttpClient client = new HttpClient();
        try
        {
            client.start();
            ContentResponse response = client.GET("http://127.0.0.1:" + TestJettyOSGiBootCore.DEFAULT_HTTP_PORT + "/index.html");
            assertEquals(HttpStatus.OK_200, response.getStatus());

            String content = new String(response.getContent());
            assertTrue(content.contains("<h1>Servlet 3.1 Test WebApp</h1>"));
            
            Request req = client.POST("http://127.0.0.1:" + TestJettyOSGiBootCore.DEFAULT_HTTP_PORT + "/test");
            response = req.send();
            content = new String(response.getContent());
            assertTrue(content.contains("<p><b>Result: <span class=\"pass\">PASS</span></p>"));
            
            response = client.GET("http://127.0.0.1:" + TestJettyOSGiBootCore.DEFAULT_HTTP_PORT + "/frag.html");
            content = new String(response.getContent());
            assertTrue(content.contains("<h1>FRAGMENT</h1>"));
        }
        finally
        {
            client.stop();
        }
    }
