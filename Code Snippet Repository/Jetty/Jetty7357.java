    @Test
    public void testDownloadWithInputStreamResponseListener() throws Exception
    {
        String content = "hello world";
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                response.getOutputStream().print(content);
            }
        });

        CountDownLatch latch = new CountDownLatch(1);
        InputStreamResponseListener listener = new InputStreamResponseListener();
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .onResponseSuccess(response -> latch.countDown())
                .send(listener);
        Response response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(200, response.getStatus());

        // Response cannot succeed until we read the content.
        Assert.assertFalse(latch.await(500, TimeUnit.MILLISECONDS));

        InputStream input = listener.getInputStream();
        Assert.assertEquals(content, IO.toString(input));

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
