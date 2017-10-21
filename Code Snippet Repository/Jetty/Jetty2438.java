    @Test
    public void testCONNECTAndMultipleGETs() throws Exception
    {
        String hostPort = "localhost:" + serverConnector.getLocalPort();
        String request = "" +
                "CONNECT " + hostPort + " HTTP/1.1\r\n" +
                "Host: " + hostPort + "\r\n" +
                "\r\n";
        try (Socket socket = newSocket())
        {
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            output.write(request.getBytes(StandardCharsets.UTF_8));
            output.flush();

            // Expect 200 OK from the CONNECT request
            HttpTester.Input in = HttpTester.from(input);
            HttpTester.Response response = HttpTester.parseResponse(in);
            Assert.assertEquals(HttpStatus.OK_200, response.getStatus());

            for (int i = 0; i < 10; ++i)
            {
                request = "" +
                        "GET /echo" + " HTTP/1.1\r\n" +
                        "Host: " + hostPort + "\r\n" +
                        "\r\n";
                output.write(request.getBytes(StandardCharsets.UTF_8));
                output.flush();

                response = HttpTester.parseResponse(in);
                Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
                Assert.assertEquals("GET /echo", response.getContent());
            }
        }
    }
