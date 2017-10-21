    @Test(timeout=60000)
    public void testMaxIdleWithRequest11() throws Exception
    {
        configureServer(new EchoHandler());
        Socket client=newSocket(_serverURI.getHost(),_serverURI.getPort());
        client.setSoTimeout(10000);

        Assert.assertFalse(client.isClosed());

        OutputStream os=client.getOutputStream();
        InputStream is=client.getInputStream();

        String content="Wibble";
        byte[] contentB=content.getBytes("utf-8");
        os.write((
                "POST /echo HTTP/1.1\r\n"+
                "host: "+_serverURI.getHost()+":"+_serverURI.getPort()+"\r\n"+
                "content-type: text/plain; charset=utf-8\r\n"+
                "content-length: "+contentB.length+"\r\n"+
        "\r\n").getBytes("utf-8"));
        os.write(contentB);
        os.flush();

        long start = System.currentTimeMillis();
        IO.toString(is);

        Thread.sleep(sleepTime);
        Assert.assertEquals(-1, is.read());

        Assert.assertTrue(System.currentTimeMillis() - start > minimumTestRuntime);
        Assert.assertTrue(System.currentTimeMillis() - start < maximumTestRuntime);
    }
