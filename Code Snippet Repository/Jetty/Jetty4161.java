    @Test
    public void testGetRequestURI_HTTP11() throws Exception
    {
        try (Socket client = newSocket(serverURI.getHost(),serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            String request = String.format("GET %s HTTP/1.1\r\nHost: %s\r\nConnection: close\r\n\r\n",rawpath,serverURI.getHost());
            os.write(request.getBytes(StandardCharsets.ISO_8859_1));
            os.flush();

            // Read the response.
            String response = readResponse(client);

            Assert.assertThat(response,Matchers.containsString("HTTP/1.1 200 OK"));
            Assert.assertThat(response,Matchers.containsString("RequestURI: " + expectedReqUri));
            Assert.assertThat(response,Matchers.containsString("QueryString: " + expectedQuery));
        }
    }
