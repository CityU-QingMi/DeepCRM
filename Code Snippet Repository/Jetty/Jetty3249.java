    @Test
    public void testPartialReadThenClose() throws Exception
    {
        server.setHandler(new PartialReaderHandler());
        server.start();

        try (final Socket socket =  new Socket("localhost",connector.getLocalPort()))
        {
            socket.setSoTimeout(1000);

            byte[] content = new byte[32*4096];
            Arrays.fill(content, (byte)88);

            OutputStream out = socket.getOutputStream();
            String header=
                "POST /?read=10 HTTP/1.1\r\n"+
                    "Host: localhost\r\n"+
                    "Content-Length: "+content.length+"\r\n"+
                    "Content-Type: bytes\r\n"+
                    "\r\n";
            byte[] h=header.getBytes(StandardCharsets.ISO_8859_1);
            out.write(h);
            out.write(content,0,4096);
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            assertThat(in.readLine(),containsString("HTTP/1.1 200 OK"));
            assertThat(in.readLine(),containsString("Content-Length:"));
            assertThat(in.readLine(),containsString("Server:"));
            in.readLine();
            assertThat(in.readLine(),containsString("XXXXXXX"));

            socket.close();
        }
    }
