    @Test
    public void testUploadWithDeferredContentAvailableCallbacksNotifiedOnce() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                IO.copy(request.getInputStream(), new ByteArrayOutputStream());
            }
        });

        final CountDownLatch latch = new CountDownLatch(1);
        final AtomicInteger succeeds = new AtomicInteger();
        try (DeferredContentProvider content = new DeferredContentProvider())
        {
            // Make the content immediately available.
            content.offer(ByteBuffer.allocate(1024), new Callback()
            {
                @Override
                public void succeeded()
                {
                    succeeds.incrementAndGet();
                }
            });

            client.newRequest("localhost", connector.getLocalPort())
                    .scheme(getScheme())
                    .content(content)
                    .send(result ->
                    {
                        if (result.isSucceeded() && result.getResponse().getStatus() == 200)
                            latch.countDown();
                    });
        }
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        Assert.assertEquals(1, succeeds.get());
    }
