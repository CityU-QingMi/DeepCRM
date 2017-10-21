    @Test
    public void testPartialRead() throws Exception
    {
        server.setHandler(new PartialReaderHandler());
        server.start();

        try (final Socket socket =  new Socket("localhost",connector.getLocalPort()))
        {
            socket.setSoTimeout(10000);

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
            out.write(content);

            header= "POST /?read=10 HTTP/1.1\r\n"+
                    "Host: localhost\r\n"+
                    "Content-Length: "+content.length+"\r\n"+
                    "Content-Type: bytes\r\n"+
                    "Connection: close\r\n"+
                    "\r\n";
            h=header.getBytes(StandardCharsets.ISO_8859_1);
            out.write(h);
            out.write(content);
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            assertThat(in.readLine(),containsString("HTTP/1.1 200 OK"));
            assertThat(in.readLine(),containsString("Content-Length: 11"));
            assertThat(in.readLine(),containsString("Server:"));
            in.readLine();
            assertThat(in.readLine(),containsString("XXXXXXX"));
            assertThat(in.readLine(),containsString("HTTP/1.1 200 OK"));
            assertThat(in.readLine(),containsString("Connection: close"));
            assertThat(in.readLine(),containsString("Content-Length: 11"));
            assertThat(in.readLine(),containsString("Server:"));
            in.readLine();
            assertThat(in.readLine(),containsString("XXXXXXX"));

        }
    }
