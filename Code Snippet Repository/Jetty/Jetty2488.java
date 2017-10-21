    @Test
    public void testExpect100ContinueRespond100ContinueDelayedRequestContent() throws Exception
    {
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                // Send the 100 Continue.
                ServletInputStream input = request.getInputStream();
                // Echo the content.
                IO.copy(input, response.getOutputStream());
            }
        });
        startProxy();
        startClient();

        byte[] content = new byte[1024];
        new Random().nextBytes(content);
        int chunk1 = content.length / 2;
        DeferredContentProvider contentProvider = new DeferredContentProvider();
        contentProvider.offer(ByteBuffer.wrap(content, 0, chunk1));
        CountDownLatch clientLatch = new CountDownLatch(1);
        client.newRequest("localhost", serverConnector.getLocalPort())
                .header(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString())
                .content(contentProvider)
                .send(new BufferingResponseListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        if (result.isSucceeded())
                        {
                            if (result.getResponse().getStatus() == HttpStatus.OK_200)
                            {
                                if (Arrays.equals(content, getContent()))
                                    clientLatch.countDown();
                            }
                        }
                    }
                });

        // Wait a while and then offer more content.
        Thread.sleep(1000);
        contentProvider.offer(ByteBuffer.wrap(content, chunk1, content.length - chunk1));
        contentProvider.close();

        Assert.assertTrue(clientLatch.await(5, TimeUnit.SECONDS));
    }
