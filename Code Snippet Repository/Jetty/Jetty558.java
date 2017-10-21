    @Test
    public void testCompleteNotInvokedUntilContentConsumed() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                ServletOutputStream output = response.getOutputStream();
                output.write(new byte[1024]);
            }
        });

        final AtomicReference<Callback> callbackRef = new AtomicReference<>();
        final CountDownLatch contentLatch = new CountDownLatch(1);
        final CountDownLatch completeLatch = new CountDownLatch(1);
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .send(new Response.Listener.Adapter()
                {
                    @Override
                    public void onContent(Response response, ByteBuffer content, Callback callback)
                    {
                        // Do not notify the callback yet.
                        callbackRef.set(callback);
                        contentLatch.countDown();
                    }

                    @Override
                    public void onComplete(Result result)
                    {
                        if (result.isSucceeded())
                            completeLatch.countDown();
                    }
                });

        Assert.assertTrue(contentLatch.await(5, TimeUnit.SECONDS));

        // Make sure the complete event is not emitted.
        Assert.assertFalse(completeLatch.await(1, TimeUnit.SECONDS));

        // Consume the content.
        callbackRef.get().succeeded();

        // Now the complete event is emitted.
        Assert.assertTrue(completeLatch.await(5, TimeUnit.SECONDS));
    }
