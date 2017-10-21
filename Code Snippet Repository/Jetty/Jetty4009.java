    @Test
    public void testAsyncConsumeAll() throws Exception
    {
        StringBuilder request = new StringBuilder(512);
        request.append("GET /ctx/path3/info HTTP/1.1\r\n")
        .append("Host: localhost\r\n")
        .append("Content-Type: text/plain\r\n")
        .append("Content-Length: 10\r\n")
        .append("\r\n")
        .append("0");
        
        int port=_port;
        try (Socket socket = new Socket("localhost",port))
        {
            socket.setSoTimeout(10000);
            OutputStream out = socket.getOutputStream();
            out.write(request.toString().getBytes(StandardCharsets.ISO_8859_1));
            
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()),102400);
            
            // response line
            String line = in.readLine();
            LOG.debug("response-line: "+line);
            Assert.assertThat(line,startsWith("HTTP/1.1 200 OK"));
            
            // Skip headers
            while (line!=null)
            {
                line = in.readLine();
                LOG.debug("header-line: "+line);
                if (line.length()==0)
                    break;
            }

            // Get body
            line = in.readLine();
            LOG.debug("body: "+line);
            Assert.assertEquals("DONE",line);

            // The connection should be aborted
            line = in.readLine();
            Assert.assertNull(line);
        }
    }
