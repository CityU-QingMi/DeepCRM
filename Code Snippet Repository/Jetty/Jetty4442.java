    @Test
    public void testHandleOptions() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        // test PUT1
        request.setMethod("OPTIONS");
        request.setVersion("HTTP/1.0");
        request.put("Host","tester");
        request.setURI("/context/file.txt");
        response = HttpTester.parseResponse(tester.getResponses(request.generate()));
        assertEquals(HttpServletResponse.SC_OK,response.getStatus());

        Set<String> options = new HashSet<String>();
        String allow=response.get("Allow");
        options.addAll(StringUtil.csvSplit(null,allow,0,allow.length()));
        assertTrue(options.contains("GET"));
        assertTrue(options.contains("POST"));
        assertTrue(options.contains("PUT"));
        assertTrue(options.contains("MOVE"));

    }
