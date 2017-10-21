    @Test
    @Ignore("")
    public void testExternalProxy() throws Exception
    {
        // Free proxy server obtained from http://hidemyass.com/proxy-list/
        String proxyHost = "81.208.25.53";
        int proxyPort = 3128;
        try
        {
            new Socket(proxyHost, proxyPort).close();
        }
        catch (Throwable x)
        {
            Assume.assumeNoException(x);
        }

        SslContextFactory sslContextFactory = new SslContextFactory();
        sslContextFactory.start();

        HttpClient httpClient = new HttpClient(newSslContextFactory());
        httpClient.getProxyConfiguration().getProxies().add(new HttpProxy(proxyHost, proxyPort));
        httpClient.start();

        try
        {
            ContentResponse response = httpClient.newRequest("https://www.google.com")
                    // Use a longer timeout, sometimes the proxy takes a while to answer
                    .timeout(20, TimeUnit.SECONDS)
                    .send();
            Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        }
        finally
        {
            httpClient.stop();
        }
    }
