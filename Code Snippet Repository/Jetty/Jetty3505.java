    @Test
    public void testAvailable() throws Exception
    {
        AvailableHandler ah = new AvailableHandler();
        configureServer(ah);
        _connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration().setDelayDispatchUntilContent(false);

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();

            os.write((
                    "GET /data?writes=1024&block=256 HTTP/1.1\r\n" +
                            "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                            "connection: close\r\n" +
                            "content-type: unknown\r\n" +
                            "content-length: 30\r\n" +
                            "\r\n"
            ).getBytes());
            os.flush();
            Thread.sleep(500);
            os.write((
                    "1234567890"
            ).getBytes());
            os.flush();

            ah._ex.exchange(null);

            os.write((
                    "abcdefghijklmnopqrst"
            ).getBytes());
            os.flush();
            Thread.sleep(500);
            ah._ex.exchange(null);

            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            // skip header
            while (reader.readLine().length() > 0) ;
            assertThat(Integer.parseInt(reader.readLine()), Matchers.equalTo(0));
            assertThat(Integer.parseInt(reader.readLine()), Matchers.equalTo(9));
            assertThat(Integer.parseInt(reader.readLine()), Matchers.equalTo(0));
            assertThat(Integer.parseInt(reader.readLine()), Matchers.greaterThan(0));
            assertThat(Integer.parseInt(reader.readLine()), Matchers.equalTo(0));
            assertEquals("1234567890abcdefghijklmnopqrst", reader.readLine());
        }
    }
