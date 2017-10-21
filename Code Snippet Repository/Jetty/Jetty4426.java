    @Test
    public void testBufferOverflowNoCRLF () throws Exception
    {
        String boundary="XyXyXy";
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;
        tester.addServlet(BoundaryServlet.class,"/testb");
        tester.setAttribute("fileName", "abc");
        tester.setAttribute("desc", "123");
        tester.setAttribute("title", "ttt");
        request.setMethod("POST");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/testb");
        request.setHeader("Content-Type","multipart/form-data; boundary="+boundary);

        String content = "--XyXyXy";

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        baos.write(content.getBytes());

        for (int i=0; i< 8500; i++) //create content that will overrun default buffer size of BufferedInputStream
        {
            baos.write('a');
        }
        request.setContent(baos.toString());

        try(StacklessLogging stackless = new StacklessLogging(ServletHandler.class, HttpChannel.class))
        {
            response = HttpTester.parseResponse(tester.getResponses(request.generate()));
            assertTrue(response.getContent().contains("Buffer size exceeded"));
            assertEquals(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, response.getStatus());
        }
    }
