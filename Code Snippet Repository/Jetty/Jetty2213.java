    @Test
    public void testBundle() throws Exception
    {
        // now test getting a static file
        HttpClient client = new HttpClient();
        try
        {
            client.start();
            
            ContentResponse response = client.GET("http://127.0.0.1:" + TestJettyOSGiBootCore.DEFAULT_HTTP_PORT + "/acme/index.html");
            assertEquals(HttpStatus.OK_200, response.getStatus());
            String content = new String(response.getContent());
            assertTrue(content.indexOf("<h1>Test OSGi WebAppA</h1>") != -1);

            response = client.GET("http://127.0.0.1:" + TestJettyOSGiBootCore.DEFAULT_HTTP_PORT + "/acme/mime");
            assertEquals(HttpStatus.OK_200, response.getStatus());
            content = new String(response.getContent());
            assertTrue(content.indexOf("MIMETYPE=application/gzip") != -1);

            response = client.GET("http://127.0.0.1:" + "9999" + "/acme/index.html");
            assertEquals(HttpStatus.OK_200, response.getStatus());
            content = new String(response.getContent());
            assertTrue(content.indexOf("<h1>Test OSGi WebAppB</h1>") != -1);
        }
        finally
        {
            client.stop();
        }

        ServiceReference[] refs = bundleContext.getServiceReferences(WebAppContext.class.getName(), null);
        assertNotNull(refs);
        assertEquals(2, refs.length);
        WebAppContext wac = (WebAppContext) bundleContext.getService(refs[0]);
        assertEquals("/acme", wac.getContextPath());
        wac = (WebAppContext) bundleContext.getService(refs[1]);
        assertEquals("/acme", wac.getContextPath());
    }
