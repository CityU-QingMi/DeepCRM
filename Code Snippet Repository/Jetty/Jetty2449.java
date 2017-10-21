    @Test
    public void testTwoExchanges() throws Exception
    {
        startTLSServer(new ServerHandler());
        startProxy();

        HttpClient httpClient = new HttpClient(newSslContextFactory());
        httpClient.getProxyConfiguration().getProxies().add(newHttpProxy());
        httpClient.start();

        try
        {
            String body = "BODY";
            ContentResponse response1 = httpClient.newRequest("localhost", serverConnector.getLocalPort())
                    .scheme(HttpScheme.HTTPS.asString())
                    .method(HttpMethod.GET)
                    .path("/echo?body=" + URLEncoder.encode(body, "UTF-8"))
                    .timeout(5, TimeUnit.SECONDS)
                    .send();

            Assert.assertEquals(HttpStatus.OK_200, response1.getStatus());
            String content = response1.getContentAsString();
            Assert.assertEquals(body, content);

            content = "body=" + body;
            ContentResponse response2 = httpClient.newRequest("localhost", serverConnector.getLocalPort())
                    .scheme(HttpScheme.HTTPS.asString())
                    .method(HttpMethod.POST)
                    .path("/echo")
                    .header(HttpHeader.CONTENT_TYPE, MimeTypes.Type.FORM_ENCODED.asString())
                    .header(HttpHeader.CONTENT_LENGTH, String.valueOf(content.length()))
                    .content(new StringContentProvider(content))
                    .timeout(5, TimeUnit.SECONDS)
                    .send();

            Assert.assertEquals(HttpStatus.OK_200, response2.getStatus());
            content = response2.getContentAsString();
            Assert.assertEquals(body, content);
        }
        finally
        {
            httpClient.stop();
        }
    }
