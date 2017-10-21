    @Test
    public void testClientRequestDoesNotSendContentProxyIdlesTimeout() throws Exception
    {
        prepareProxy();
        int idleTimeout = 2000;
        proxyConnector.setIdleTimeout(idleTimeout);

        prepareServer(new EchoHttpServlet());

        try (Socket socket = new Socket("localhost", proxyConnector.getLocalPort()))
        {
            String serverHostPort = "localhost:" + serverConnector.getLocalPort();
            String request = "" +
                    "GET http://" + serverHostPort + " HTTP/1.1\r\n" +
                    "Host: " + serverHostPort + "\r\n" +
                    "Content-Length: 1\r\n" +
                    "\r\n";
            OutputStream output = socket.getOutputStream();
            output.write(request.getBytes("UTF-8"));
            output.flush();

            // Do not send the promised content, wait to idle timeout.

            socket.setSoTimeout(2 * idleTimeout);
    
            HttpTester.Response response = HttpTester.parseResponse(socket.getInputStream());
            Assert.assertTrue(response.getStatus() >= 500);
            String connectionHeader = response.get("connection");
            Assert.assertNotNull(connectionHeader);
            Assert.assertTrue(connectionHeader.contains("close"));
            Assert.assertEquals(-1, socket.getInputStream().read());
        }
    }
