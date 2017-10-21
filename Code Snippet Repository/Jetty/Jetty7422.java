    @Test
    public void test8_2_ExpectWithBody() throws Exception
    {
        // Expect with body

        StringBuffer req3 = new StringBuffer();
        req3.append("GET /echo/R1 HTTP/1.1\n");
        req3.append("Host: localhost\n");
        req3.append("Expect: 100-continue\n"); // Valid Expect header.
        req3.append("Content-Type: text/plain\n");
        req3.append("Content-Length: 8\n");
        req3.append("Connection: close\n");
        req3.append("\n");
        req3.append("123456\r\n"); // Body

        // Should only expect 1 response.
        // The existence of 2 responses usually means a bad "HTTP/1.1 100" was received.
        HttpTester.Response response = http.request(req3);

        assertEquals("8.2.3 expect 100", HttpStatus.OK_200, response.getStatus());
    }
