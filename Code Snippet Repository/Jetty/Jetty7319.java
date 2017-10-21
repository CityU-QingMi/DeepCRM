    private void test_Expect100Continue_WithContent_RespondError(final int error) throws Exception
    {
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.sendError(error);
            }
        });

        byte[] content1 = new byte[10240];
        byte[] content2 = new byte[16384];
        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(newURI())
                .header(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString())
                .content(new BytesContentProvider(content1, content2))
                .send(new BufferingResponseListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        Assert.assertTrue(result.isFailed());
                        Assert.assertNotNull(result.getRequestFailure());
                        Assert.assertNull(result.getResponseFailure());
                        byte[] content = getContent();
                        Assert.assertNotNull(content);
                        Assert.assertTrue(content.length > 0);
                        Assert.assertEquals(error, result.getResponse().getStatus());
                        latch.countDown();
                    }
                });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
