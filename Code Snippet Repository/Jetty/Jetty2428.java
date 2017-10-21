    @Test
    public void testCONNECTAndPOSTWithBigBody() throws Exception
    {
        // Use a longer idle timeout since this test
        // may take a long time on slower machines.
        long idleTimeout = 5 * 60 * 1000;
        serverConnector.setIdleTimeout(idleTimeout);
        proxyConnector.setIdleTimeout(idleTimeout);
        connectHandler.setIdleTimeout(idleTimeout);

        String hostPort = "localhost:" + serverConnector.getLocalPort();

        String request = "" +
                "CONNECT " + hostPort + " HTTP/1.1\r\n" +
                "Host: " + hostPort + "\r\n" +
                "\r\n";
        try (Socket socket = newSocket())
        {
            socket.setSoTimeout((int)idleTimeout);
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            output.write(request.getBytes(StandardCharsets.UTF_8));
            output.flush();

            // Expect 200 OK from the CONNECT request
            HttpTester.Input in = HttpTester.from(input);
            HttpTester.Response response = HttpTester.parseResponse(in);
            Assert.assertEquals(HttpStatus.OK_200, response.getStatus());

            StringBuilder body = new StringBuilder();
            String chunk = "0123456789ABCDEF";
            for (int i = 0; i < 1024 * 1024; ++i)
                body.append(chunk);

            request = "" +
                    "POST /echo HTTP/1.1\r\n" +
                    "Host: " + hostPort + "\r\n" +
                    "Content-Length: " + body.length() + "\r\n" +
                    "\r\n" +
                    body;
            output.write(request.getBytes(StandardCharsets.UTF_8));
            output.flush();

            response = HttpTester.parseResponse(in);
            Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
            Assert.assertEquals("POST /echo\r\n" + body, response.getContent());
        }
    }
