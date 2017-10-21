    @Test
    public void test_Expect100Continue_WithConcurrentDeferredContent_Respond100Continue() throws Exception
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

        final byte[] data = new byte[]{0, 1, 2, 3, 4, 5, 6, 7};
        final DeferredContentProvider content = new DeferredContentProvider();

        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(newURI())
                .header(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString())
                .onRequestHeaders(request ->
                {
                    content.offer(ByteBuffer.wrap(data));
                    content.close();
                })
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

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
