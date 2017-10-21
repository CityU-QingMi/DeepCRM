    @Test
    public void testHandleDelete() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        // test PUT1
        request.setMethod("PUT");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setURI("/context/file.txt");
        request.setHeader("Content-Type","text/plain");
        String data1="How Now BROWN COW!!!!";
        request.setContent(data1);
        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_CREATED,response.getStatus());

        File file=new File(_dir,"file.txt");
        assertTrue(file.exists());
        try (InputStream fis = new FileInputStream(file))
        {
            assertEquals(data1,IO.toString(fis));
        }

        request.setMethod("DELETE");
        request.setURI("/context/file.txt");
        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_NO_CONTENT,response.getStatus());

        assertTrue(!file.exists());

        request.setMethod("DELETE");
        request.setURI("/context/file.txt");
        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_FORBIDDEN,response.getStatus());
    }
