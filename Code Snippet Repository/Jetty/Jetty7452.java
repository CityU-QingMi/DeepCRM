    @Test
    public void test14_39_TEDeflate() throws Exception
    {
        if (STRICT)
        {
            String specId;

            // Deflate not accepted
            StringBuffer req2 = new StringBuffer();
            req2.append("GET /rfc2616-webapp/solutions.html HTTP/1.1\n");
            req2.append("Host: localhost\n");
            req2.append("TE: deflate\n"); // deflate not accepted
            req2.append("Connection: close\n");
            req2.append("\n");

            HttpTester.Response response = http.request(req2);
            specId = "14.39 TE Header";
            assertEquals(specId,HttpStatus.NOT_IMPLEMENTED_501, response.getStatus()); // Error on TE (deflate not supported)
        }
    }
