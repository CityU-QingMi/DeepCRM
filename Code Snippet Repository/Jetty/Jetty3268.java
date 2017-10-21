    @Test(timeout=60000)
    public void testMaxIdleWithRequest10() throws Exception
    {
        configureServer(new HelloWorldHandler());
        
        Socket client=newSocket(_serverURI.getHost(),_serverURI.getPort());
        client.setSoTimeout(10000);

        Assert.assertFalse(client.isClosed());

        OutputStream os=client.getOutputStream();
        InputStream is=client.getInputStream();

        os.write((
                "GET / HTTP/1.0\r\n"+
                "host: "+_serverURI.getHost()+":"+_serverURI.getPort()+"\r\n"+
                "connection: keep-alive\r\n"+
        "\r\n").getBytes("utf-8"));
        os.flush();

        long start = System.currentTimeMillis();
        IO.toString(is);

        Thread.sleep(sleepTime);
        Assert.assertEquals(-1, is.read());

        Assert.assertTrue(System.currentTimeMillis() - start > minimumTestRuntime);
        Assert.assertTrue(System.currentTimeMillis() - start < maximumTestRuntime);
    }
