    @Test
    public void test3_6_4() throws Throwable
    {
        // Chunked and keep alive
        StringBuffer req4 = new StringBuffer();
        req4.append("GET /echo/R1 HTTP/1.1\n");
        req4.append("Host: localhost\n");
        req4.append("Transfer-Encoding: chunked\n");
        req4.append("Content-Type: text/plain\n");
        req4.append("Connection: keep-alive\n"); // keep-alive
        req4.append("\n");
        req4.append("3;\n"); // 3 chars
        req4.append("123\n");
        req4.append("3;\n"); // 3 chars
        req4.append("456\n");
        req4.append("0;\n\n"); // 0 chars

        req4.append("GET /echo/R2 HTTP/1.1\n");
        req4.append("Host: localhost\n");
        req4.append("Connection: close\n"); // close
        req4.append("\n");

        List<HttpTester.Response> responses = http.requests(req4);
        Assert.assertEquals("Response Count",2,responses.size());

        HttpTester.Response response = responses.get(0); // Response 1
        assertEquals("3.6.1 Transfer Codings / Response 1 Code", HttpStatus.OK_200, response.getStatus());
        assertTrue("3.6.1 Transfer Codings / Chunked String", response.getContent().contains("123456\n")); // Complete R1 string

        response = responses.get(1); // Response 2
        assertEquals("3.6.1 Transfer Codings / Response 2 Code", HttpStatus.OK_200, response.getStatus());
        assertEquals("3.6.1 Transfer Codings / No Body","",response.getContent());
    }
