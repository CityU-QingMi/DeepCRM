    @Test
    public void test3_6() throws Throwable
    {
        // Chunk last
        StringBuffer req1 = new StringBuffer();
        req1.append("GET /tests/R1 HTTP/1.1\n");
        req1.append("Host: localhost\n");
        req1.append("Transfer-Encoding: chunked,identity\n"); // Invalid Transfer-Encoding
        req1.append("Content-Type: text/plain\n");
        req1.append("Connection: close\n");
        req1.append("\r\n");
        req1.append("5;\r\n");
        req1.append("123\r\n\r\n");
        req1.append("0;\r\n\r\n");

        HttpTester.Response response = http.request(req1);
        
        assertEquals("3.6 Transfer Coding / Bad 400",HttpStatus.BAD_REQUEST_400,response.getStatus());
    }
