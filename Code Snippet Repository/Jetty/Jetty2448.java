    @Test
    public void testOneExchange() throws Exception
    {
        startTLSServer(new ServerHandler());
        startProxy();

        HttpClient httpClient = new HttpClient(newSslContextFactory());
        httpClient.getProxyConfiguration().getProxies().add(newHttpProxy());
        httpClient.start();

        try
        {
            // Use a numeric host to test the URI of the CONNECT request.
            // URIs such as host:80 may interpret "host" as the scheme,
            // but when the host is numeric it is not a valid URI.
            String host = "127.0.0.1";
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
