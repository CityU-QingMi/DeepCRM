    private void assertByteRange(String rangedef, String expectedRange, String expectedBody) throws IOException
    {
        StringBuffer req1 = new StringBuffer();
        req1.append("GET /rfc2616-webapp/alpha.txt HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Range: ").append(rangedef).append("\n");
        req1.append("Connection: close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);

        String msg = "Partial (Byte) Range: '" + rangedef + "'";
        assertEquals(msg,HttpStatus.PARTIAL_CONTENT_206, response.getStatus());
        // It might be strange to see a "Content-Range' response header to a 'Range' request,
        // but this is appropriate per the RFC2616 spec.
        assertEquals(msg,"bytes " + expectedRange, response.get("Content-Range"));
        assertTrue(msg,response.getContent().contains(expectedBody));
    }
