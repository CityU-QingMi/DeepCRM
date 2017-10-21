    @Test
    public void testDelayedDispatchRequestWithDelayedFirstContentIdleTimeoutFires() throws Exception
    {
        httpConfig.setDelayDispatchUntilContent(true);
        CountDownLatch handlerLatch = new CountDownLatch(1);
        start(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            protected void doNonErrorHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                handlerLatch.countDown();
            }
        });
        long idleTimeout = 2500;
        setServerIdleTimeout(idleTimeout);

        CountDownLatch resultLatch = new CountDownLatch(1);
        client.POST(newURI())
                .content(new DeferredContentProvider())
                .send(result ->
                {
                    if (result.isFailed())
                        resultLatch.countDown();
                });

        // We did not send the content, the request was not
        // dispatched, the server should have idle timed out.
        Assert.assertFalse(handlerLatch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));
        Assert.assertTrue(resultLatch.await(5, TimeUnit.SECONDS));
    }
