    @Test
    public void testNoBody()
    throws Exception
    {
        String boundary="XyXyXy";
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;
        
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/dump");
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);

        try(StacklessLogging stackless = new StacklessLogging(ServletHandler.class, HttpChannel.class))
        {
            response = HttpTester.parseResponse(tester.getResponses(request.generate()));
            assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getStatus());
            assertTrue(response.getContent().indexOf("Missing content")>=0);
        }
    }
