    @Test
    public void testGzipNotMicro() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        request.setMethod("GET");
        request.setURI("/ctx/micro");
        request.setVersion("HTTP/1.0");
        request.setHeader("Host","tester");
        request.setHeader("Accept-Encoding","gzip");

        response = HttpTester.parseResponse(_connector.getResponse(request.generate()));

        assertThat(response.getStatus(),is(200));
        assertThat(response.get("Content-Encoding"),not(containsString("gzip")));
        assertThat(response.get("ETag"),is(__contentETag));
        assertThat(response.get("Vary"),is("Accept-Encoding"));

        InputStream testIn = new ByteArrayInputStream(response.getContentBytes());
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        IO.copy(testIn,testOut);

        assertEquals(__micro, testOut.toString("UTF8"));
    }
