    @Test
    public void test14_23_Http11_NoHost() throws Exception
    {
        // HTTP/1.1 400 (bad request) with no host

        StringBuffer req2 = new StringBuffer();
        req2.append("GET /tests/R1.txt HTTP/1.1\n");
        req2.append("Connection: close\n");
        req2.append("\n");

        HttpTester.Response response = http.request(req2);
        assertEquals("14.23 HTTP/1.1 - No Host",HttpStatus.BAD_REQUEST_400, response.getStatus());
    }
