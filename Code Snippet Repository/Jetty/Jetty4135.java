    @Test
    public void testNotGzipHandler() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        request.setMethod("GET");
        request.setURI("/ctx/content?vary=Other");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");

        response = HttpTester.parseResponse(_connector.getResponse(request.generate()));
        
        assertThat(response.getStatus(),is(200));
        assertThat(response.get("Content-Encoding"),not(equalToIgnoringCase("gzip")));
        assertThat(response.get("ETag"),is(__contentETag));
        assertThat(response.getValuesList("Vary"),Matchers.contains("Other","Accept-Encoding"));

        InputStream testIn = new ByteArrayInputStream(response.getContentBytes());
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        IO.copy(testIn,testOut);

        assertEquals(__content, testOut.toString("UTF8"));
    }
