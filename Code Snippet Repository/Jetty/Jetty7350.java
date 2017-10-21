    @Test(expected = AsynchronousCloseException.class)
    public void testInputStreamResponseListenerClosedBeforeContent() throws Exception
    {
        AtomicReference<AsyncContext> contextRef = new AtomicReference<>();
        start(new AbstractHandler()
        {
            @Override
            public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
            {
                baseRequest.setHandled(true);
                contextRef.set(request.startAsync());
                response.flushBuffer();
            }
        });

        CountDownLatch latch = new CountDownLatch(1);
        InputStreamResponseListener listener = new InputStreamResponseListener()
        {
            @Override
            public void onContent(Response response, ByteBuffer content, Callback callback)
            {
                super.onContent(response, content, new Callback()
                {
                    @Override
                    public void failed(Throwable x)
                    {
                        latch.countDown();
                        callback.failed(x);
                    }
                });
            }
        };
        client.newRequest("localhost", connector.getLocalPort())
                .scheme(getScheme())
                .send(listener);

        Response response = listener.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());

        InputStream input = listener.getInputStream();
        input.close();

        AsyncContext asyncContext = contextRef.get();
        asyncContext.getResponse().getOutputStream().write(new byte[1024]);
        asyncContext.complete();

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));

        input.read(); // Throws
    }
