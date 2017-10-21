    public static void main(String[] args) throws Exception
    {
        java.io.File path = new java.io.File("/tmp/jetty.sock");
        java.io.File content = new java.io.File("/tmp/data.txt");
        
        String method = "GET";
        int content_length = 0;
        String body = null;
        if (content.exists())
        {
            method = "POST";
            body = IO.readToString(content);
            content_length = body.length();
        }
        String data = method+" / HTTP/1.1\r\n"
            + "Host: unixsock\r\n"
            + "Content-Length: "+content_length+"\r\n"
            + "Connection: close\r\n"
            + "\r\n";
        if (body!=null)
            data += body;

        while (true)
        {
            UnixSocketAddress address = new UnixSocketAddress(path);
            UnixSocketChannel channel = UnixSocketChannel.open(address);
            System.out.println("connected to " + channel.getRemoteSocketAddress());

            PrintWriter w = new PrintWriter(new OutputStreamWriter(Channels.newOutputStream(channel),StandardCharsets.ISO_8859_1));
            InputStreamReader r = new InputStreamReader(Channels.newInputStream(channel));

            w.print(data);
            w.flush();

            CharBuffer result = CharBuffer.allocate(4096);
            String total="";
            int l = 0;
            while (l>=0)
            {
                if (l>0)
                {
                    result.flip();
                    total += result.toString();
                }
                result.clear();
                l = r.read(result);
            }
            System.out.println("read from server: " + total);
        }
    }
