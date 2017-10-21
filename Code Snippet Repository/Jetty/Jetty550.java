    @Test
    public void testHTTP10WithKeepAliveAndNoContentLength() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                // Send the headers at this point, then write the content
                response.flushBuffer();
                response.getOutputStream().print("TEST");
            }
        });

        FuturePromise<Connection> promise = new FuturePromise<>();
        Destination destination = client.getDestination(scheme, "localhost", connector.getLocalPort());
        destination.newConnection(promise);
        try (Connection connection = promise.get(5, TimeUnit.SECONDS))
        {
            long timeout = 5000;
            Request request = client.newRequest(destination.getHost(), destination.getPort())
                    .scheme(destination.getScheme())
                    .version(HttpVersion.HTTP_1_0)
                    .header(HttpHeader.CONNECTION, HttpHeaderValue.KEEP_ALIVE.asString())
                    .timeout(timeout, TimeUnit.MILLISECONDS);

            FutureResponseListener listener = new FutureResponseListener(request);
            connection.send(request, listener);
            ContentResponse response = listener.get(2 * timeout, TimeUnit.MILLISECONDS);

            Assert.assertEquals(200, response.getStatus());
            // The parser notifies end-of-content and therefore the CompleteListener
            // before closing the connection, so we need to wait before checking
            // that the connection is closed to avoid races.
            Thread.sleep(1000);
            Assert.assertTrue(connection.isClosed());
        }
    }
