    @Test
    public void testAbortLongPollAsync() throws Exception
    {
        final long delay = 1000;
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                try
                {
                    baseRequest.setHandled(true);
                    if (request.getDispatcherType() != DispatcherType.ERROR)
                        TimeUnit.MILLISECONDS.sleep(2 * delay);
                }
                catch (InterruptedException x)
                {
                    throw new ServletException(x);
                }
            }
        });

        final Throwable cause = new Exception();
        final CountDownLatch latch = new CountDownLatch(1);
        Request request = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .timeout(3 * delay, TimeUnit.MILLISECONDS);
        request.send(result ->
        {
            Assert.assertTrue(result.isFailed());
            Assert.assertSame(cause, result.getFailure());
            latch.countDown();
        });

        TimeUnit.MILLISECONDS.sleep(delay);

        request.abort(cause);

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
