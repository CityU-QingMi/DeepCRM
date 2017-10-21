    @Test
    public void test3_6_2() throws Throwable
    {
        // Chunked
        StringBuffer req2 = new StringBuffer();
        req2.append("GET /echo/R1 HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("Transfer-Encoding: chunked\n");
        req2.append("Content-Type: text/plain\n");
        req2.append("\n");
        req2.append("2;\n"); // 2 chars
        req2.append("12\n");
        req2.append("3;\n"); // 3 chars
        req2.append("345\n");
        req2.append("0;\n\n");

        req2.append("GET /echo/R2 HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("Transfer-Encoding: chunked\n");
        req2.append("Content-Type: text/plain\n");
        req2.append("\n");
        req2.append("4;\n"); // 4 chars
        req2.append("6789\n");
        req2.append("5;\n"); // 5 chars
        req2.append("abcde\n");
        req2.append("0;\n\n"); // 0 chars

        req2.append("GET /echo/R3 HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("Connection: close\n");
        req2.append("\n");

        List<HttpTester.Response> responses = http.requests(req2);
        Assert.assertEquals("Response Count",3,responses.size());

        HttpTester.Response response = responses.get(0); // Response 1
        assertEquals("3.6.1 Transfer Codings / Response 1 Code", HttpStatus.OK_200, response.getStatus());
        assertTrue("3.6.1 Transfer Codings / Chunked String", response.getContent().contains("12345\n"));

        response = responses.get(1); // Response 2
        assertEquals("3.6.1 Transfer Codings / Response 2 Code", HttpStatus.OK_200, response.getStatus());
        assertThat("3.6.1 Transfer Codings / Chunked String",response.getContent(),Matchers.containsString("6789abcde\n"));

        response = responses.get(2); // Response 3
        assertEquals("3.6.1 Transfer Codings / Response 3 Code", HttpStatus.OK_200, response.getStatus());
        assertEquals("3.6.1 Transfer Codings / No Body","",response.getContent());
    }
