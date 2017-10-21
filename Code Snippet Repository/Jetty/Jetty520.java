    @Test
    public void test_307_WithRequestContent() throws Exception
    {
        start(new RedirectHandler());

        byte[] data = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
        ContentResponse response = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .method(HttpMethod.POST)
                .path("/307/localhost/done")
                .content(new ByteBufferContentProvider(ByteBuffer.wrap(data)))
                .timeout(5, TimeUnit.SECONDS)
                .send();
        Assert.assertNotNull(response);
        Assert.assertEquals(200, response.getStatus());
        Assert.assertFalse(response.getHeaders().containsKey(HttpHeader.LOCATION.asString()));
        Assert.assertArrayEquals(data, response.getContent());
    }
