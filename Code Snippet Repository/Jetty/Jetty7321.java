    @Test
    public void test_Redirect_WithExpect100Continue_WithContent() throws Exception
    {
        // A request with Expect: 100-Continue cannot receive non-final responses like 3xx

        final String data = "success";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                if (request.getRequestURI().endsWith("/done"))
                {
                    // Send 100-Continue and consume the content
                    IO.copy(request.getInputStream(), new ByteArrayOutputStream());
                    response.getOutputStream().print(data);
                }
                else
                {
                    // Send a redirect
                    response.sendRedirect("/done");
                }
            }
        });

        byte[] content = new byte[10240];
        final CountDownLatch latch = new CountDownLatch(1);
        client.newRequest(newURI())
                .method(HttpMethod.POST)
                .path("/redirect")
                .header(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString())
                .content(new BytesContentProvider(content))
                .send(new BufferingResponseListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        Assert.assertTrue(result.isFailed());
                        Assert.assertNotNull(result.getRequestFailure());
                        Assert.assertNull(result.getResponseFailure());
                        Assert.assertEquals(302, result.getResponse().getStatus());
                        latch.countDown();
                    }
                });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
