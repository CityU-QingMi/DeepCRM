    @Test
    public void testProxyClosesConnection() throws Exception
    {
        startTLSServer(new ServerHandler());
        startProxy(new ConnectHandler()
        {
            @Override
            protected void handleConnect(Request baseRequest, HttpServletRequest request, HttpServletResponse response, String serverAddress)
            {
                ((HttpConnection)baseRequest.getHttpChannel().getHttpTransport()).close();
            }
        });

        HttpClient httpClient = new HttpClient(newSslContextFactory());
        httpClient.getProxyConfiguration().getProxies().add(newHttpProxy());
        httpClient.start();

        try
        {
            httpClient.newRequest("localhost", serverConnector.getLocalPort())
                    .scheme(HttpScheme.HTTPS.asString())
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            // Expected
        }
        finally
        {
            httpClient.stop();
        }
    }
