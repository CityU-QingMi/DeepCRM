    @Test
    public void testProxyDown() throws Exception
    {
        startServer(new EmptyHttpServlet());
        startProxy();
        startClient();
        // Shutdown the proxy
        proxy.stop();

        try
        {
            client.newRequest("localhost", serverConnector.getLocalPort())
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            Assert.assertThat(x.getCause(), Matchers.instanceOf(ConnectException.class));
        }
    }
