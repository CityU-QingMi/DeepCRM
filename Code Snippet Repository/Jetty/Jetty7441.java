    @Test
    public void test14_23_Http10_NoHostHeader() throws Exception
    {
        // HTTP/1.0 OK with no host

        StringBuffer req1 = new StringBuffer();
        req1.append("GET /tests/R1.txt HTTP/1.0\n");
        req1.append("Connection: close\n");
        req1.append("\n");

        HttpTester.Response response = http.request(req1);
        assertEquals("14.23 HTTP/1.0 - No Host", HttpStatus.OK_200, response.getStatus());
    }
