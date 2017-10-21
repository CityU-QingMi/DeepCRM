    @Slow
    @Test
    public void test_Expect100Continue_WithContent_WithResponseFailure_Before100Continue() throws Exception
    {
        final long idleTimeout = 1000;
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                try
                {
                    TimeUnit.MILLISECONDS.sleep(2 * idleTimeout);
                }
                catch (InterruptedException x)
                {
                    throw new ServletException(x);
                }
            }
        });

        client.setIdleTimeout(idleTimeout);

        byte[] content = new byte[1024];
        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(newURI())
                .header(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString())
                .content(new BytesContentProvider(content))
                .send(new BufferingResponseListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        Assert.assertTrue(result.isFailed());
                        Assert.assertNotNull(result.getRequestFailure());
                        Assert.assertNotNull(result.getResponseFailure());
                        latch.countDown();
                    }
                });

        Assert.assertTrue(latch.await(3 * idleTimeout, TimeUnit.MILLISECONDS));
    }
