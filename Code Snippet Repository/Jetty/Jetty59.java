    @Test
    public void testClientNotSupportingALPNServerSpeaksDefaultProtocol() throws Exception
    {
        startServer(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            protected void doNonErrorHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
            }
        });

        SslContextFactory sslContextFactory = new SslContextFactory(true);
        sslContextFactory.start();
        SSLContext sslContext = sslContextFactory.getSslContext();
        try (SSLSocket client = (SSLSocket)sslContext.getSocketFactory().createSocket("localhost", connector.getLocalPort()))
        {
            client.setUseClientMode(true);
            client.setSoTimeout(5000);
            client.startHandshake();

            OutputStream output = client.getOutputStream();
            output.write(("" +
                    "GET / HTTP/1.1\r\n" +
                    "Host: localhost\r\n" +
                    "Connection: close\r\n" +
                    "\r\n" +
                    "").getBytes(StandardCharsets.UTF_8));
            output.flush();

            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String line = reader.readLine();
            Assert.assertTrue(line.contains(" 200 "));
            while (true)
            {
                if (reader.readLine() == null)
                    break;
            }
        }
    }
