    @Test
    public void testHTTP_1_1_Simple() throws Exception
    {
        try (Socket client = new Socket("localhost", connector.getLocalPort()))
        {
            client.getOutputStream().write("GET /one HTTP/1.1\r\nHost: localhost\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1));
            client.getOutputStream().write("GET /two HTTP/1.1\r\nHost: localhost\r\nConnection: close\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1));
            client.getOutputStream().flush();

            String response = IO.toString(client.getInputStream());

            assertThat(response, containsString("HTTP/1.1 200 OK"));
            assertThat(response, containsString("Hello from Jetty using HTTP/1.1"));
            assertThat(response, containsString("uri=/one"));
            assertThat(response, containsString("uri=/two"));
        }
    }
