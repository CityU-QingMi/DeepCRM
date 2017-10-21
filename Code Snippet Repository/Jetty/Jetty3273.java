    @Test(timeout=60000)
    public void testMaxIdleNothingSent() throws Exception
    {
        configureServer(new EchoHandler());
        Socket client=newSocket(_serverURI.getHost(),_serverURI.getPort());
        client.setSoTimeout(10000);
        InputStream is=client.getInputStream();
        Assert.assertFalse(client.isClosed());

        Thread.sleep(sleepTime);
        long start = System.currentTimeMillis();
        try
        {
            IO.toString(is);
            Assert.assertEquals(-1, is.read());
        }
        catch(SSLException e)
        {
            // e.printStackTrace();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        Assert.assertTrue(System.currentTimeMillis() - start < maximumTestRuntime);

    }
