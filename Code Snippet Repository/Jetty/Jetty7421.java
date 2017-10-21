    @Test
    public void test8_2_ExpectInvalid() throws Exception
    {
        // Expect Failure

        StringBuffer req2 = new StringBuffer();
        req2.append("GET /echo/R1 HTTP/1.1\n");
        req2.append("Host: localhost\n");
        req2.append("Expect: unknown\n"); // Invalid Expect header.
        req2.append("Content-Type: text/plain\n");
        req2.append("Content-Length: 8\n");
        req2.append("\n"); 
        req2.append("12345678\n"); 

        HttpTester.Response response = http.request(req2);

        assertEquals("8.2.3 expect failure",HttpStatus.EXPECTATION_FAILED_417, response.getStatus());
    }
