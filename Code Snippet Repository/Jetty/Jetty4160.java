    @Test
    public void testGetRequestURI_HTTP10() throws Exception
    {
        try (Socket client = newSocket(serverURI.getHost(),serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            String request = String.format("GET %s HTTP/1.0\r\n\r\n",rawpath);
            os.write(request.getBytes(StandardCharsets.ISO_8859_1));
            os.flush();

            // Read the response.
            String response = readResponse(client);

            // TODO: is HTTP/1.1 response appropriate for a HTTP/1.0 request?
            Assert.assertThat(response,Matchers.containsString("HTTP/1.1 200 OK"));
            Assert.assertThat(response,Matchers.containsString("RequestURI: " + expectedReqUri));
            Assert.assertThat(response,Matchers.containsString("QueryString: " + expectedQuery));
        }
    }
