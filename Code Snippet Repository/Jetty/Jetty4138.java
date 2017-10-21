    @Test
    public void testGzipNotMicroChunked() throws Exception
    {
        // generated and parsed test
        HttpTester.Request request = HttpTester.newRequest();
        HttpTester.Response response;

        request.setMethod("GET");
        request.setURI("/ctx/microchunked");
        request.setVersion("HTTP/1.1");
        request.setHeader("Host","tester");
        request.setHeader("Accept-Encoding","gzip");

        ByteBuffer rawresponse = _connector.getResponse(request.generate());
        // System.err.println(BufferUtil.toUTF8String(rawresponse));
        response = HttpTester.parseResponse(rawresponse);

        assertThat(response.getStatus(),is(200));
        assertThat(response.get("Transfer-Encoding"),containsString("chunked"));
        assertThat(response.get("Content-Encoding"),containsString("gzip"));
        assertThat(response.get("Vary"),is("Accept-Encoding"));

        InputStream testIn = new GZIPInputStream(new ByteArrayInputStream(response.getContentBytes()));
        ByteArrayOutputStream testOut = new ByteArrayOutputStream();
        IO.copy(testIn,testOut);

        assertEquals(__micro, testOut.toString("UTF8"));
    }
