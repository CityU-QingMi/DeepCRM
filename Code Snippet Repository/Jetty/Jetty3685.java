    @Test
    public void testReadInput() throws Exception
    {
        prepareServer(new ReadHandler()).start();

        try(Socket client = clientSocketProvider.newSocket("localhost", _connector.getLocalPort()))
        {
            client.setSoTimeout(10000);
            OutputStream os = client.getOutputStream();
            InputStream is = client.getInputStream();
    
            String request = "" +
                    "GET / HTTP/1.0\r\n" +
                    "Host: localhost\r\n" +
                    "Content-Length: 10\r\n" +
                    "\r\n" +
                    "0123456789\r\n";
            os.write(request.getBytes(StandardCharsets.UTF_8));
            os.flush();
    
            String response = IO.toString(is);
            assertEquals(-1, is.read());
            assertThat(response, containsString("200 OK"));
            assertThat(response, containsString("Read Input 10"));
        }
    }
