    @Test
    public void testJspDump() throws Exception
    {
        // TestOSGiUtil.debugBundles(bundleContext);
        HttpClient client = new HttpClient();
        try
        {
            client.start();
            ContentResponse response = client.GET("http://127.0.0.1:" + TestJettyOSGiBootCore.DEFAULT_HTTP_PORT + "/jsp/jstl.jsp");
            
            assertEquals(HttpStatus.OK_200, response.getStatus());
            String content = new String(response.getContent());
            assertTrue(content.contains("JSTL Example"));           
        }
        finally
        {
            client.stop();
        }
    }
