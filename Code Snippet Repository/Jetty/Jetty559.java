    @Test
    public void testRequestSentOnlyAfterConnectionOpen() throws Exception
    {
        startServer(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
            }
        });

        final AtomicBoolean open = new AtomicBoolean();
        client = new HttpClient(new HttpClientTransportOverHTTP()
        {
            @Override
            protected HttpConnectionOverHTTP newHttpConnection(EndPoint endPoint, HttpDestination destination, Promise<Connection> promise)
            {
                return new HttpConnectionOverHTTP(endPoint, destination, promise)
                {
                    @Override
                    public void onOpen()
                    {
                        open.set(true);
                        super.onOpen();
                    }
                };
            }
        }, sslContextFactory);
        client.start();

        final CountDownLatch latch = new CountDownLatch(2);
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .onRequestBegin(request ->
                {
                    Assert.assertTrue(open.get());
                    latch.countDown();
                })
                .send(result ->
                {
                    if (result.isSucceeded())
                        latch.countDown();
                });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
