    @Test
    public void testBlockingReadBadChunk() throws Exception
    {
        configureServer(new ReadHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            client.setSoTimeout(600000);
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();

            os.write((
                "GET /data HTTP/1.1\r\n" +
                "host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                "content-type: unknown\r\n" +
                "transfer-encoding: chunked\r\n" +
                "\r\n"
            ).getBytes());
            os.flush();
            Thread.sleep(50);
            os.write((
                "a\r\n" +
                "123456890\r\n"
                ).getBytes());
            os.flush();
            
            Thread.sleep(50);
            os.write((
                "4\r\n" +
                "abcd\r\n"
                ).getBytes());
            os.flush();
            
            Thread.sleep(50);
            os.write((
                "X\r\n" +
                "abcd\r\n"
                ).getBytes());
            os.flush();
            
            HttpTester.Response response = HttpTester.parseResponse(HttpTester.from(is));
            
            assertThat(response.getStatus(),is(200));
            assertThat(response.getContent(),containsString("EofException"));
            assertThat(response.getContent(),containsString("Early EOF"));
        }
    }
