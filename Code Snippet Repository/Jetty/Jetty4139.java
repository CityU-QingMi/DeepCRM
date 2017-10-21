    @Test
    public void testETagNotGzipHandler() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        request.setMethod("GET");
        request.setURI("/ctx/content");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setHeader("If-None-Match",__contentETag);
        request.setHeader("accept-encoding","gzip");

        response = HttpTester.parseResponse(_connector.getResponse(request.generate()));

        assertThat(response.getStatus(),is(304));
        assertThat(response.get("Content-Encoding"),not(Matchers.equalToIgnoringCase("gzip")));
        assertThat(response.get("ETag"),is(__contentETag));
    }
