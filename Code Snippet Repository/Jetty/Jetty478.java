    @Test
    public void simpleGetWithHostnameVerificationDisabledTest() throws Exception
    {
        clientSslContextFactory.setEndpointIdentificationAlgorithm(null);
        String uri = "https://localhost:" + connector.getLocalPort() + "/";
        try
        {
            client.GET(uri);
        }
        catch (ExecutionException e)
        {
            Assert.fail("SSLHandshake should work just fine as hostname verification is disabled! " + e.getMessage());
        }
    }
