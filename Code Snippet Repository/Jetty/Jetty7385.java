    @Test
    public void testBlockingReadWithMinimumDataRateAboveLimit() throws Exception
    {
        int bytesPerSecond = 20;
        httpConfig.setMinRequestDataRate(bytesPerSecond);
        CountDownLatch handlerLatch = new CountDownLatch(1);
        start(new AbstractHandler.ErrorDispatchHandler()
        {
            @Override
            protected void doNonErrorHandle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                ServletInputStream input = request.getInputStream();
                while (true)
                {
                    int read = input.read();
                    if (read < 0)
                        break;
                }
                handlerLatch.countDown();
            }
        });

        DeferredContentProvider contentProvider = new DeferredContentProvider();
        CountDownLatch resultLatch = new CountDownLatch(1);
        client.newRequest(newURI())
                .content(contentProvider)
                .send(result ->
                {
                    if (result.getResponse().getStatus() == HttpStatus.OK_200)
                        resultLatch.countDown();
                });

        for (int i = 0; i < 3; ++i)
        {
            contentProvider.offer(ByteBuffer.allocate(bytesPerSecond * 2));
            Thread.sleep(2500);
        }
        contentProvider.close();

        Assert.assertTrue(handlerLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(resultLatch.await(5, TimeUnit.SECONDS));
    }
