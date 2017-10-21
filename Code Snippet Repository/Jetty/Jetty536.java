    @Test
    public void test_QueuedRequest_IsSent_WhenPreviousRequestClosedConnection() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                if (target.endsWith("/one"))
                    baseRequest.getHttpChannel().getEndPoint().close();
                else
                    baseRequest.setHandled(true);
            }
        });

        client.setMaxConnectionsPerDestination(1);

        try (StacklessLogging stackless = new StacklessLogging(org.eclipse.jetty.server.HttpChannel.class))
        {
            final CountDownLatch latch = new CountDownLatch(2);
            client.newRequest("localhost", connector.getLocalPort())
            .scheme(scheme)
            .path("/one")
            .onResponseFailure((response, failure) -> latch.countDown())
            .send(null);

            client.newRequest("localhost", connector.getLocalPort())
            .scheme(scheme)
            .path("/two")
            .onResponseSuccess(response ->
            {
                Assert.assertEquals(200, response.getStatus());
                latch.countDown();
            })
            .send(null);

            Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        }
    }
