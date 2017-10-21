    public void asyncReadTest(int contentSize, int chunkSize, int chunks, int delayMS) throws Exception
    {
        String tst=contentSize+","+chunkSize+","+chunks+","+delayMS;
        //System.err.println(tst);

        try(final Socket socket =  new Socket("localhost",connector.getLocalPort()))
        {

            byte[] content = new byte[contentSize];
            Arrays.fill(content, (byte)120);

            OutputStream out = socket.getOutputStream();
            out.write("POST / HTTP/1.1\r\n".getBytes());
            out.write("Host: localhost\r\n".getBytes());
            out.write(("Content-Length: "+content.length+"\r\n").getBytes());
            out.write("Content-Type: bytes\r\n".getBytes());
            out.write("Connection: close\r\n".getBytes());
            out.write("\r\n".getBytes());
            out.flush();

            int offset=0;
            for (int i=0;i<chunks;i++)
            {
                out.write(content,offset,chunkSize);
                offset+=chunkSize;
                Thread.sleep(delayMS);
            }
            out.write(content,offset,content.length-offset);

            out.flush();

            InputStream in = socket.getInputStream();
            String response = IO.toString(in);
            assertTrue(tst,response.indexOf("200 OK")>0);

            long total=__total.poll(30,TimeUnit.SECONDS);
            assertEquals(tst,content.length, total);
        }
    }
