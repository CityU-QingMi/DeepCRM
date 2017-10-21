    @Test
    public void test8_2_ExpectNormal() throws Exception
    {
        // Expect 100

        StringBuffer req4 = new StringBuffer();
        req4.append("GET /echo/R1 HTTP/1.1\n");
        req4.append("Host: localhost\n");
        req4.append("Connection: close\n");
        req4.append("Expect: 100-continue\n"); // Valid Expect header.
        req4.append("Content-Type: text/plain\n");
        req4.append("Content-Length: 7\n");
        req4.append("\n"); // No body

        Socket sock = http.open();
        try
        {
            http.send(sock,req4);

            http.setTimeoutMillis(2000);
            HttpTester.Response response = http.readAvailable(sock);
            assertEquals("8.2.3 expect 100",HttpStatus.CONTINUE_100,response.getStatus());

            http.send(sock,"654321\n"); // Now send the data
            response = http.read(sock);

            assertEquals("8.2.3 expect 100", HttpStatus.OK_200, response.getStatus());
            assertThat("8.2.3 expect 100",response.getContent(),Matchers.containsString("654321\n"));
        }
        finally
        {
            http.close(sock);
        }
    }
