    @Test
    public void testHead() throws Exception
    {
        configureServer(new EchoHandler(false));

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();

            os.write((
                    "POST /R1 HTTP/1.1\015\012" +
                            "Host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\r\n" +
                            "content-type: text/plain; charset=utf-8\r\n" +
                            "content-length: 10\r\n" +
                            "\015\012" +
                            "123456789\n" +

                            "HEAD /R2 HTTP/1.1\015\012" +
                            "Host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\015\012" +
                            "content-type: text/plain; charset=utf-8\r\n" +
                            "content-length: 10\r\n" +
                            "\015\012" +
                            "ABCDEFGHI\n" +

                            "POST /R3 HTTP/1.1\015\012" +
                            "Host: " + _serverURI.getHost() + ":" + _serverURI.getPort() + "\015\012" +
                            "content-type: text/plain; charset=utf-8\r\n" +
                            "content-length: 10\r\n" +
                            "Connection: close\015\012" +
                            "\015\012" +
                            "abcdefghi\n"

            ).getBytes(StandardCharsets.ISO_8859_1));


            String in = IO.toString(is);
            Assert.assertThat(in,containsString("123456789"));
            Assert.assertThat(in,not(containsString("ABCDEFGHI")));
            Assert.assertThat(in,containsString("abcdefghi"));
        }
    }
