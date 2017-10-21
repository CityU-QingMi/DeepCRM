    @Test
    public void test_ClientConnectionClose_ServerNoConnectionClose_ClientCloses() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.setContentLength(0);
                response.flushBuffer();

                try
                {
                    // Delay the server from sending the TCP FIN.
                    Thread.sleep(1000);
                }
                catch (InterruptedException x)
                {
                    throw new InterruptedIOException();
                }
            }
        });

        String host = "localhost";
        int port = connector.getLocalPort();

        HttpDestinationOverHTTP destination = (HttpDestinationOverHTTP)client.getDestination(scheme, host, port);
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();

        ContentResponse response = client.newRequest(host, port)
                .scheme(scheme)
                .header(HttpHeader.CONNECTION, HttpHeaderValue.CLOSE.asString())
                .onRequestSuccess(request ->
                {
                    HttpConnectionOverHTTP connection = (HttpConnectionOverHTTP)connectionPool.getActiveConnections().iterator().next();
                    Assert.assertFalse(connection.getEndPoint().isOutputShutdown());
                })
                .onResponseHeaders(r -> r.getHeaders().remove(HttpHeader.CONNECTION))
                .send();

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertEquals(0, connectionPool.getConnectionCount());
    }
