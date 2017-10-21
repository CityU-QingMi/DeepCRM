    @Test
    public void testExtended() throws Exception
    {
        configureServer(new DispatchedAtHandler());

        try (Socket client = newSocket(_serverURI.getHost(), _serverURI.getPort()))
        {
            OutputStream os = client.getOutputStream();

            long start=System.currentTimeMillis();
            os.write("GET / HTTP/1.0\r\n".getBytes(StandardCharsets.ISO_8859_1));
            os.flush();
            Thread.sleep(200);
            long end=System.currentTimeMillis();
            os.write("\r\n".getBytes(StandardCharsets.ISO_8859_1));
            
            // Read the response.
            String response = readResponse(client);

            Assert.assertThat(response, Matchers.containsString("HTTP/1.1 200 OK"));
            Assert.assertThat(response, Matchers.containsString("DispatchedAt="));
            
            String s=response.substring(response.indexOf("DispatchedAt=")+13);
            s=s.substring(0,s.indexOf('\n'));
            long dispatched=Long.valueOf(s);
            
            Assert.assertThat(dispatched, Matchers.greaterThanOrEqualTo(start));
            Assert.assertThat(dispatched, Matchers.lessThan(end));
        }
    }
