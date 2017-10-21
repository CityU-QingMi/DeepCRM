    @Test
    public void testIncludeGzipHandler() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        request.setMethod("GET");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setHeader("accept-encoding","gzip");
        request.setURI("/ctx/include");

        response = HttpTester.parseResponse(_connector.getResponse(request.generate()));

        assertThat(response.getStatus(),is(200));
        assertThat(response.get("Content-Encoding"),Matchers.equalToIgnoringCase("gzip"));
        assertThat(response.get("ETag"),nullValue());
        assertThat(response.get("Vary"),is("Accept-Encoding"));

        InputStream testIn = new GZIPInputStream(new ByteArrayInputStream(response.getContentBytes()));
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        IO.copy(testIn,testOut);

        assertEquals(__icontent, testOut.toString("UTF8"));
    }
