    @Test
    public void testMultilineResponseHeaderValue() throws Exception
    {
        String actualPathInfo = "%0A%20Content-Type%3A%20image/png%0A%20Content-Length%3A%208%0A%20%0A%20yuck<!--";

        HttpTester.Request request = new HttpTester.Request();
        request.setMethod("GET");
        request.setURI("/multiline/" + actualPathInfo);
        request.setVersion(HttpVersion.HTTP_1_1);
        request.setHeader("Connection", "close");
        request.setHeader("Host", "test");

        ByteBuffer responseBuffer = connector.getResponse(request.generate());
        // System.err.println(BufferUtil.toUTF8String(responseBuffer));
        HttpTester.Response response = HttpTester.parseResponse(responseBuffer);

        // Now test for properly formatted HTTP Response Headers.
        assertThat("Response Code",response.getStatus(),is(200));
        assertThat("Response Header Content-Type",response.get("Content-Type"),is("text/plain;charset=UTF-8"));

        String expected = actualPathInfo.replaceAll("%0A", " "); // replace OBS fold with space
        expected = URLDecoder.decode(expected, "utf-8"); // decode the rest
        expected = expected.trim(); // trim whitespace at start/end
        assertThat("Response Header X-example", response.get("X-Example"), is(expected));
    }
