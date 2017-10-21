    @Test
    public void testCONNECTBadHostPort() throws Exception
    {
        String invalidHostname = "badHost.webtide.com";

        try
        {
            InetAddress address = InetAddress.getByName(invalidHostname);
            StringBuilder err = new StringBuilder();
            err.append("DNS Hijacking detected: ");
            err.append(invalidHostname).append(" should have not returned a valid IP address [");
            err.append(address.getHostAddress()).append("].  ");
            err.append("Fix your DNS provider to have this test pass.");
            err.append("\nFor more info see https://en.wikipedia.org/wiki/DNS_hijacking");
            Assert.assertNull(err.toString(), address);
        }
        catch (UnknownHostException e)
        {
            // expected path
        }

        String hostPort = String.format("%s:%d", invalidHostname, serverConnector.getLocalPort());
        String request = "" +
                "CONNECT " + hostPort + " HTTP/1.1\r\n" +
                "Host: " + hostPort + "\r\n" +
                "\r\n";
        try (Socket socket = newSocket())
        {
            socket.setSoTimeout(30000);
            OutputStream output = socket.getOutputStream();
            InputStream input = socket.getInputStream();

            output.write(request.getBytes(StandardCharsets.UTF_8));
            output.flush();

            // Expect 500 OK from the CONNECT request
            HttpTester.Input in = HttpTester.from(input);
            HttpTester.Response response = HttpTester.parseResponse(in);
            Assert.assertEquals("Response Code", HttpStatus.INTERNAL_SERVER_ERROR_500, response.getStatus());
        }
    }
