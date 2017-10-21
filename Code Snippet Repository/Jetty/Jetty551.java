    @Test
    public void testHTTP10WithKeepAliveAndNoContent() throws Exception
    {
        start(new EmptyServerHandler());

        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .version(HttpVersion.HTTP_1_0)
                .header(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE.asString())
                .timeout(5, TimeUnit.SECONDS)
                .send();

        Assert.assertEquals(200, response.getStatus());
        Assert.assertTrue(response.getHeaders().contains(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE.asString()));
    }
