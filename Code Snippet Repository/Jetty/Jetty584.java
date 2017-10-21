    @Test
    public void testIPv6Host() throws Exception
    {
        start(new EmptyServerHandler());

        String host = "::1";
        Request request = client.newRequest(host, connector.getLocalPort())
                .scheme(scheme)
                .timeout(5, TimeUnit.SECONDS);

        Assert.assertEquals(host, request.getHost());
        StringBuilder uri = new StringBuilder();
        URIUtil.appendSchemeHostPort(uri, scheme, host, connector.getLocalPort());
        Assert.assertEquals(uri.toString(), request.getURI().toString());

        Assert.assertEquals(HttpStatus.OK_200, request.send().getStatus());
    }
