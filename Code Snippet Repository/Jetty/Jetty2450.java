    @Test
    public void testProxyDown() throws Exception
    {
        startTLSServer(new ServerHandler());
        startProxy();
        int proxyPort = proxyConnector.getLocalPort();
        stopProxy();

        HttpClient httpClient = new HttpClient(newSslContextFactory());
        httpClient.getProxyConfiguration().getProxies().add(new HttpProxy(new Origin.Address("localhost", proxyPort), proxySslContextFactory != null));
        httpClient.start();

        try
        {
            String body = "BODY";
            httpClient.newRequest("localhost", serverConnector.getLocalPort())
                    .scheme(HttpScheme.HTTPS.asString())
                    .method(HttpMethod.GET)
                    .path("/echo?body=" + URLEncoder.encode(body, "UTF-8"))
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            Assert.assertThat(x.getCause(), Matchers.instanceOf(ConnectException.class));
        }
        finally
        {
            httpClient.stop();
        }
    }
