    @Test
    public void testContextHandlerAsOSGiService() throws Exception
    {
        // now test the context
        HttpClient client = new HttpClient();
        try
        {
            client.start();
            ContentResponse response = client.GET("http://127.0.0.1:" + TestJettyOSGiBootCore.DEFAULT_HTTP_PORT + "/acme/index.html");
            assertEquals(HttpStatus.OK_200, response.getStatus());

            String content = new String(response.getContent());
            assertTrue(content.indexOf("<h1>Test OSGi Context</h1>") != -1);
        }
        finally
        {
            client.stop();
        }

        ServiceReference[] refs = bundleContext.getServiceReferences(ContextHandler.class.getName(), null);
        assertNotNull(refs);
        assertEquals(1, refs.length);
        ContextHandler ch = (ContextHandler) bundleContext.getService(refs[0]);
        assertEquals("/acme", ch.getContextPath());

        // Stop the bundle with the ContextHandler in it and check the jetty
        // Context is destroyed for it.
        // TODO: think of a better way to communicate this to the test, other
        // than checking stderr output
        Bundle testWebBundle = TestOSGiUtil.getBundle(bundleContext, "org.eclipse.jetty.osgi.testcontext");
        assertNotNull("Could not find the org.eclipse.jetty.test-jetty-osgi-context.jar bundle", testWebBundle);
        assertTrue("The bundle org.eclipse.jetty.testcontext is not correctly resolved", testWebBundle.getState() == Bundle.ACTIVE);
        testWebBundle.stop();
    }
