    @Test
    public void testAsyncResponseContentBackPressure() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                // Large write to generate multiple DATA frames.
                response.getOutputStream().write(new byte[256 * 1024]);
            }
        });

        CountDownLatch completeLatch = new CountDownLatch(1);
        AtomicInteger counter = new AtomicInteger();
        AtomicReference<Callback> callbackRef = new AtomicReference<>();
        AtomicReference<CountDownLatch> latchRef = new AtomicReference<>(new CountDownLatch(1));
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .onResponseContentAsync((response, content, callback) ->
                {
                    if (counter.incrementAndGet() == 1)
                    {
                        callbackRef.set(callback);
                        latchRef.get().countDown();
                    }
                    else
                    {
                        callback.succeeded();
                    }
                })
                .send(result -> completeLatch.countDown());

        Assert.assertTrue(latchRef.get().await(5, TimeUnit.SECONDS));
        // Wait some time to verify that back pressure is applied correctly.
        Thread.sleep(1000);
        Assert.assertEquals(1, counter.get());
        callbackRef.get().succeeded();

        Assert.assertTrue(completeLatch.await(5, TimeUnit.SECONDS));
    }
