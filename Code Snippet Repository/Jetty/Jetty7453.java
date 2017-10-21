    @Test
    public void test3_6_3() throws Throwable
    {
        // Chunked
        StringBuffer req3 = new StringBuffer();
        req3.append("POST /echo/R1 HTTP/1.1\n");
        req3.append("Host: localhost\n");
        req3.append("Transfer-Encoding: chunked\n");
        req3.append("Content-Type: text/plain\n");
        req3.append("\n");
        req3.append("3;\n"); // 3 chars
        req3.append("fgh\n");
        req3.append("3;\n"); // 3 chars
        req3.append("Ijk\n");
        req3.append("0;\n\n"); // 0 chars

        req3.append("POST /echo/R2 HTTP/1.1\n");
        req3.append("Host: localhost\n");
        req3.append("Transfer-Encoding: chunked\n");
        req3.append("Content-Type: text/plain\n");
        req3.append("\n");
        req3.append("4;\n"); // 4 chars
        req3.append("lmno\n");
        req3.append("5;\n"); // 5 chars
        req3.append("Pqrst\n");
        req3.append("0;\n\n"); // 0 chars

        req3.append("GET /echo/R3 HTTP/1.1\n");
        req3.append("Host: localhost\n");
        req3.append("Connection: close\n");
        req3.append("\n");

        List<HttpTester.Response> responses = http.requests(req3);
        Assert.assertEquals("Response Count",3,responses.size());

        HttpTester.Response response = responses.get(0); // Response 1
        assertEquals("3.6.1 Transfer Codings / Response 1 Code", HttpStatus.OK_200, response.getStatus());
        assertTrue("3.6.1 Transfer Codings / Chunked String", response.getContent().contains("fghIjk\n")); // Complete R1 string

        response = responses.get(1); // Response 2
        assertEquals("3.6.1 Transfer Codings / Response 2 Code", HttpStatus.OK_200, response.getStatus());
        assertTrue("3.6.1 Transfer Codings / Chunked String", response.getContent().contains("lmnoPqrst\n")); // Complete R2 string

        response = responses.get(2); // Response 3
        assertEquals("3.6.1 Transfer Codings / Response 3 Code", HttpStatus.OK_200, response.getStatus());
        assertEquals("3.6.1 Transfer Codings / No Body","",response.getContent());

    }
