    @Test
    public void testClientRequestStallsHeadersProxyIdlesTimeout() throws Exception
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
                    "Host: " + serverHostPort + "\r\n";
            // Don't sent the \r\n that would signal the end of the headers.
            OutputStream output = socket.getOutputStream();
            output.write(request.getBytes("UTF-8"));
            output.flush();

            // Wait for idle timeout to fire.

            socket.setSoTimeout(2 * idleTimeout);
            InputStream input = socket.getInputStream();
            Assert.assertEquals(-1, input.read());
        }
    }
