    @Test
    public void testOPTIONS() throws Exception
    {
        configureServer(new OptionsHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            os.write(("OPTIONS * HTTP/1.1\r\n"
                    + "Host: "+_serverURI.getHost()+"\r\n"
                    + "Connection: close\r\n"
                    + "\r\n").getBytes(StandardCharsets.ISO_8859_1));
            os.flush();

            // Read the response.
            String response = readResponse(client);

            Assert.assertThat(response, Matchers.containsString("HTTP/1.1 200 OK"));
            Assert.assertThat(response, Matchers.containsString("Allow: GET"));
        }
        
        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            os.write(("GET * HTTP/1.1\r\n"
                    + "Host: "+_serverURI.getHost()+"\r\n"
                    + "Connection: close\r\n"
                    + "\r\n").getBytes(StandardCharsets.ISO_8859_1));
            os.flush();

            // Read the response.
            String response = readResponse(client);

            Assert.assertThat(response, Matchers.containsString("HTTP/1.1 400 "));
            Assert.assertThat(response, Matchers.not(Matchers.containsString("Allow: ")));
        }
    }
