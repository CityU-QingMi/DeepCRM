    @Test
    public void testPropertiesAreForwarded() throws Exception
    {
        HTTP2Client http2Client = new HTTP2Client();
        HttpClient httpClient = new HttpClient(new HttpClientTransportOverHTTP2(http2Client), null);
        Executor executor = new QueuedThreadPool();
        httpClient.setExecutor(executor);
        httpClient.setConnectTimeout(13);
        httpClient.setIdleTimeout(17);

        httpClient.start();

        Assert.assertTrue(http2Client.isStarted());
        Assert.assertSame(httpClient.getExecutor(), http2Client.getExecutor());
        Assert.assertSame(httpClient.getScheduler(), http2Client.getScheduler());
        Assert.assertSame(httpClient.getByteBufferPool(), http2Client.getByteBufferPool());
        Assert.assertEquals(httpClient.getConnectTimeout(), http2Client.getConnectTimeout());
        Assert.assertEquals(httpClient.getIdleTimeout(), http2Client.getIdleTimeout());

        httpClient.stop();

        Assert.assertTrue(http2Client.isStopped());
    }
