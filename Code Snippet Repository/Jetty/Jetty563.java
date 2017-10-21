    private void assertCopyRequest(Request original)
    {
        Request copy = client.copyRequest((HttpRequest) original, original.getURI());
        Assert.assertEquals(original.getURI(), copy.getURI());
        Assert.assertEquals(original.getMethod(), copy.getMethod());
        Assert.assertEquals(original.getVersion(), copy.getVersion());
        Assert.assertEquals(original.getContent(), copy.getContent());
        Assert.assertEquals(original.getIdleTimeout(), copy.getIdleTimeout());
        Assert.assertEquals(original.getTimeout(), copy.getTimeout());
        Assert.assertEquals(original.isFollowRedirects(), copy.isFollowRedirects());
        Assert.assertEquals(original.getHeaders(), copy.getHeaders());
    }
