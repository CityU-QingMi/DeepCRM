    @Test
    public void test14_23_ValidHost() throws Exception
    {
        // HTTP/1.1 - Valid host

        StringBuffer req3 = new StringBuffer();
        req3.append("GET /tests/R1.txt HTTP/1.1\n");
        req3.append("Host: localhost\n");
        req3.append("Connection: close\n");
        req3.append("\n");

        HttpTester.Response response = http.request(req3);
        assertEquals("14.23 HTTP/1.1 - Valid Host", HttpStatus.OK_200, response.getStatus());
    }
