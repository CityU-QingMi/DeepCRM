    @Test
    public void testUploadWithDeferredContentProviderRacingWithSend() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, org.eclipse.jetty.server.Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                IO.copy(request.getInputStream(), response.getOutputStream());
            }
        });

        final CountDownLatch latch = new CountDownLatch(1);
        final byte[] data = new byte[512];
        final DeferredContentProvider content = new DeferredContentProvider()
        {
            @Override
            public void setListener(Listener listener)
            {
                super.setListener(listener);
                // Simulate a concurrent call
                offer(ByteBuffer.wrap(data));
                close();
            }
        };

        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .content(content)
                .send(new BufferingResponseListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        if (result.isSucceeded() &&
                                result.getResponse().getStatus() == 200 &&
                                Arrays.equals(data, getContent()))
                            latch.countDown();
                    }
                });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
