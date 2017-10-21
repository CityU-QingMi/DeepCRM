    @Test
    public void testGET_Raw() throws Exception
    {
        StringBuffer rawRequest = new StringBuffer();
        rawRequest.append("GET /tests/alpha.txt HTTP/1.1\r\n");
        rawRequest.append("Host: localhost\r\n");
        rawRequest.append("Connection: close\r\n");
        rawRequest.append("\r\n");

        Socket sock = new Socket(InetAddress.getLocalHost(),serverPort);
        sock.setSoTimeout(5000); // 5 second timeout;

        DEBUG("--raw-request--\n" + rawRequest);
        InputStream in = new ByteArrayInputStream(rawRequest.toString().getBytes());

        // Send request
        IO.copy(in,sock.getOutputStream());

        // Collect response
        String rawResponse = IO.toString(sock.getInputStream());
        DEBUG("--raw-response--\n" + rawResponse);
        
        HttpTester.Response response = HttpTester.parseResponse(rawResponse);

        assertEquals(HttpStatus.OK_200, response.getStatus());

        assertTrue(response.getContent().contains("ABCDEFGHIJKLMNOPQRSTUVWXYZ\n"));
    }
