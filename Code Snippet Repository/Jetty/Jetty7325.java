    @Slow
    @Test
    public void test_Expect100Continue_WithInitialAndDeferredContent_Respond100Continue() throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                // Send 100-Continue and echo the content
                IO.copy(request.getInputStream(), response.getOutputStream());
            }
        });

        final byte[] chunk1 = new byte[]{0, 1, 2, 3};
        final byte[] chunk2 = new byte[]{4, 5, 6, 7};
        final byte[] data = new byte[chunk1.length + chunk2.length];
        System.arraycopy(chunk1, 0, data, 0, chunk1.length);
        System.arraycopy(chunk2, 0, data, chunk1.length, chunk2.length);

        final CountDownLatch latch = new CountDownLatch(1);
        DeferredContentProvider content = new DeferredContentProvider(ByteBuffer.wrap(chunk1));
        client.newRequest(newURI())
                .header(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString())
                .content(content)
                .send(new BufferingResponseListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        Assert.assertArrayEquals(data, getContent());
                        latch.countDown();
                    }
                });

        Thread.sleep(1000);

        content.offer(ByteBuffer.wrap(chunk2));
        content.close();

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
