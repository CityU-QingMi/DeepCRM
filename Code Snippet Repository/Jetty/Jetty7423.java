    @Test
    public void test8_2_UnexpectWithBody() throws Exception
    {
        // Expect with body

        StringBuffer req3 = new StringBuffer();
        req3.append("GET /redirect/R1 HTTP/1.1\n");
        req3.append("Host: localhost\n");
        req3.append("Expect: 100-continue\n"); // Valid Expect header.
        req3.append("Content-Type: text/plain\n");
        req3.append("Content-Length: 8\n");
        req3.append("\n");
        req3.append("123456\r\n");
        req3.append("GET /echo/R1 HTTP/1.1\n");
        req3.append("Host: localhost\n");
        req3.append("Content-Type: text/plain\n");
        req3.append("Content-Length: 8\n");
        req3.append("Connection: close\n");
        req3.append("\n");
        req3.append("87654321"); // Body

        List<HttpTester.Response> responses = http.requests(req3);
        
        // System.err.println(responses);
        
        HttpTester.Response response=responses.get(0);
        // System.err.println(response);
        
        assertEquals("8.2.3 ignored no 100",302, response.getStatus());
        assertEquals("close",response.get("Connection"));
        assertEquals(1,responses.size());
    }
