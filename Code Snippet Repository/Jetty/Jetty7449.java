    private void assertBadByteRange(String rangedef) throws IOException
    {
        StringBuffer req1 = new StringBuffer();
        req1.append("GET /rfc2616-webapp/alpha.txt HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Range: ").append(rangedef).append("\n"); // Invalid range
        req1.append("Connection: close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);

        assertEquals("BadByteRange: '" + rangedef + "'",HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE_416, response.getStatus());
    }
