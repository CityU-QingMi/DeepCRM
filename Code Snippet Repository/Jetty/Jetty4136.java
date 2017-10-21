    @Test
    public void testGzipHandler() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        request.setMethod("GET");
        request.setURI("/ctx/content?vary=Accept-Encoding,Other");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setHeader("accept-encoding","gzip");

        response = HttpTester.parseResponse(_connector.getResponse(request.generate()));

        assertThat(response.getStatus(),is(200));
        assertThat(response.get("Content-Encoding"),Matchers.equalToIgnoringCase("gzip"));
        assertThat(response.get("ETag"),is(__contentETagGzip));
        assertThat(response.getCSV("Vary",false),Matchers.contains("Accept-Encoding","Other"));

        InputStream testIn = new GZIPInputStream(new ByteArrayInputStream(response.getContentBytes()));
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        IO.copy(testIn,testOut);

        assertEquals(__content, testOut.toString("UTF8"));
    }
