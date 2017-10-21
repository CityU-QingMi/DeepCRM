    @Test
    public void simpleGetWithHostnameVerificationEnabledTest() throws Exception
    {
        clientSslContextFactory.setEndpointIdentificationAlgorithm("HTTPS");
        String uri = "https://localhost:" + connector.getLocalPort() + "/";
        try
        {
            client.GET(uri);
            Assert.fail("sending request to client should have failed with an Exception!");
        }
        catch (ExecutionException x)
        {
            Throwable cause = x.getCause();
            Assert.assertThat(cause, Matchers.instanceOf(SSLHandshakeException.class));
            Throwable root = cause.getCause().getCause();
            Assert.assertThat(root, Matchers.instanceOf(CertificateException.class));
        }
    }
