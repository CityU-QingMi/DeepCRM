    @Ignore
    @Test
    public void testExternalServer() throws Exception
    {
        HTTP2Client http2Client = new HTTP2Client();
        SslContextFactory sslContextFactory = new SslContextFactory();
        HttpClient httpClient = new HttpClient(new HttpClientTransportOverHTTP2(http2Client), sslContextFactory);
        Executor executor = new QueuedThreadPool();
        httpClient.setExecutor(executor);

        httpClient.start();

//        ContentResponse response = httpClient.GET("https://http2.akamai.com/");
        ContentResponse response = httpClient.GET("https://webtide.com/");

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());

        httpClient.stop();
    }
