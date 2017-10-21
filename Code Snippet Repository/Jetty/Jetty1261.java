    @Test
    public void testHTTP_1_0_Simple() throws Exception
    {
        try (Socket client = new Socket("localhost", connector.getLocalPort()))
        {
            client.getOutputStream().write("GET / HTTP/1.0\r\n\r\n".getBytes(StandardCharsets.ISO_8859_1));
            client.getOutputStream().flush();

            String response = IO.toString(client.getInputStream());

            assertThat(response, containsString("HTTP/1.1 200 OK"));
            assertThat(response, containsString("Hello from Jetty using HTTP/1.0"));
        }
    }
