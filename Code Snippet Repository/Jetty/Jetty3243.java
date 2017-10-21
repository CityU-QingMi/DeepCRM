    @Test
    public void testPipelined() throws Exception
    {
        server.setHandler(new AsyncStreamHandler());
        server.start();

        try (final Socket socket =  new Socket("localhost",connector.getLocalPort()))
        {
            socket.setSoTimeout(1000);

            byte[] content = new byte[32*4096];
            Arrays.fill(content, (byte)120);

            OutputStream out = socket.getOutputStream();
            String header=
                "POST / HTTP/1.1\r\n"+
                    "Host: localhost\r\n"+
                    "Content-Length: "+content.length+"\r\n"+
                    "Content-Type: bytes\r\n"+
                    "\r\n";
            byte[] h=header.getBytes(StandardCharsets.ISO_8859_1);
            out.write(h);
            out.write(content);


            header=
                "POST / HTTP/1.1\r\n"+
                    "Host: localhost\r\n"+
                    "Content-Length: "+content.length+"\r\n"+
                    "Content-Type: bytes\r\n"+
                    "Connection: close\r\n"+
                    "\r\n";
            h=header.getBytes(StandardCharsets.ISO_8859_1);
            out.write(h);
            out.write(content);
            out.flush();

            InputStream in = socket.getInputStream();
            String response = IO.toString(in);
            assertTrue(response.indexOf("200 OK")>0);

            long total=__total.poll(5,TimeUnit.SECONDS);
            assertEquals(content.length, total);
            total=__total.poll(5,TimeUnit.SECONDS);
            assertEquals(content.length, total);
        }
    }
