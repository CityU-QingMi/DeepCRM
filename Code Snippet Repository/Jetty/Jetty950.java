    @Test
    public void testClientNotSupportingALPNServerSpeaksDefaultProtocol() throws Exception
    {
        InetSocketAddress address = prepare();

        SslContextFactory sslContextFactory = newSslContextFactory();
        sslContextFactory.start();
        SSLContext sslContext = sslContextFactory.getSslContext();
        try (SSLSocket client = (SSLSocket)sslContext.getSocketFactory().createSocket(address.getAddress(), address.getPort()))
        {
            client.setUseClientMode(true);
            client.setSoTimeout(5000);

            ALPN.put(client, new ALPN.ClientProvider()
            {
                @Override
                public void unsupported()
                {
                }

                @Override
                public List<String> protocols()
                {
                    return null;
                }

                @Override
                public void selected(String s)
                {
                }
            });

            client.startHandshake();

            // Verify that the server really speaks http/1.1

            OutputStream output = client.getOutputStream();
            output.write(("" +
                    "GET / HTTP/1.1\r\n" +
                    "Host: localhost:" + address.getPort() + "\r\n" +
                    "\r\n" +
                    "").getBytes(StandardCharsets.UTF_8));
            output.flush();

            InputStream input = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
            String line = reader.readLine();
            Assert.assertTrue(line.contains(" 404 "));
        }
    }
