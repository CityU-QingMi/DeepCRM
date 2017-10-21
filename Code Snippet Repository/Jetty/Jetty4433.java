    @Test
    public void testFinalBoundaryOnly() throws Exception
    {
        
        tester.addServlet(NullServlet.class,"/null");       
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        // test GET
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/null");
        
        String delimiter = "\r\n";
        final String boundary = "MockMultiPartTestBoundary";
        String content = 
                delimiter +
                "Hello world" +
                delimiter +        // Two delimiter markers, which make an empty line.
                delimiter +
                "--" + boundary + "--" + delimiter;
        
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);
        request.setContent(content);
        
        try(StacklessLogging stackless = new StacklessLogging(ServletHandler.class, HttpChannel.class))
        {
            response = HttpTester.parseResponse(tester.getResponses(request.generate()));
            assertEquals(HttpServletResponse.SC_OK,response.getStatus());
        }
    } 
