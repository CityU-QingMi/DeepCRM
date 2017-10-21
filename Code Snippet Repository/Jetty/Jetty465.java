    @Test
    public void testExternalSiteRedirect() throws Exception
    {
        String host = "twitter.com";
        int port = 443;

        // Verify that we have connectivity
        assumeCanConnectTo(host, port);

        ContentResponse response = client.newRequest(host, port)
                .scheme(HttpScheme.HTTPS.asString())
                .path("/twitter")
                .send();
        Assert.assertEquals(200, response.getStatus());
    }
