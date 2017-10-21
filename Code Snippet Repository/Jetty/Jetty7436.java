    private void assertPartialContentRange(String rangedef, String expectedRange, String expectedBody) throws IOException
    {
        // server should ignore all range headers which include
        // at least one syntactically invalid range

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /rfc2616-webapp/alpha.txt HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Range: ").append(rangedef).append("\n"); // Invalid range
        req1.append("Connection: close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);

        String msg = "Partial Range: '" + rangedef + "'";
        assertEquals(msg,HttpStatus.PARTIAL_CONTENT_206, response.getStatus());
        assertEquals(msg,"bytes " + expectedRange, response.get("Content-Range"));
        assertTrue(msg,response.getContent().contains(expectedBody));
    }
