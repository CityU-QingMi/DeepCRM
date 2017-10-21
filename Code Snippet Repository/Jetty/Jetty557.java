    @Test
    public void testRequestRetries() throws Exception
    {
        final int maxRetries = 3;
        final AtomicInteger requests = new AtomicInteger();
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                int count = requests.incrementAndGet();
                if (count == maxRetries)
                    baseRequest.setHandled(true);
                consume(request.getInputStream(), true);
            }
        });

        final CountDownLatch latch = new CountDownLatch(1);
        new RetryListener(client, scheme, "localhost", connector.getLocalPort(), maxRetries)
        {
            @Override
            protected void completed(Result result)
            {
                latch.countDown();
            }
        }.perform();

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
