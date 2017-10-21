    private void testProxyAuthentication(String realm, ConnectHandler connectHandler, boolean includeAddress) throws Exception
    {
        startTLSServer(new ServerHandler());
        startProxy(connectHandler);

        HttpClient httpClient = new HttpClient(newSslContextFactory());
        HttpProxy httpProxy = newHttpProxy();
        if (includeAddress)
            httpProxy.getIncludedAddresses().add("localhost:" + serverConnector.getLocalPort());
        httpClient.getProxyConfiguration().getProxies().add(httpProxy);
        URI uri = URI.create((proxySslContextFactory == null ? "http" : "https") + "://localhost:" + proxyConnector.getLocalPort());
        httpClient.getAuthenticationStore().addAuthentication(new BasicAuthentication(uri, realm, "proxyUser", "proxyPassword"));
        httpClient.start();

        try
        {
            String host = "localhost";
            String body = "BODY";
            ContentResponse response = httpClient.newRequest(host, serverConnector.getLocalPort())
                    .scheme(HttpScheme.HTTPS.asString())
                    .method(HttpMethod.GET)
                    .path("/echo?body=" + URLEncoder.encode(body, "UTF-8"))
                    .timeout(5, TimeUnit.SECONDS)
                    .send();

            Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
            String content = response.getContentAsString();
            Assert.assertEquals(body, content);
        }
        finally
        {
            httpClient.stop();
        }
    }
