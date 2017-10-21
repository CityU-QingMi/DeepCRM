    @Test
    public void testExpect100ContinueRespond100ContinueSomeRequestContentThenFailure() throws Exception
    {
        CountDownLatch serverLatch = new CountDownLatch(1);
        startServer(new HttpServlet()
        {
            @Override
            protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
            {
                // Send the 100 Continue.
                ServletInputStream input = request.getInputStream();
                try
                {
                    // Echo the content.
                    IO.copy(input, response.getOutputStream());
                }
                catch (IOException x)
                {
                    serverLatch.countDown();
                }
            }
        });
        startProxy();
        startClient();

        long idleTimeout = 1000;
        client.setIdleTimeout(idleTimeout);

        byte[] content = new byte[1024];
        new Random().nextBytes(content);
        int chunk1 = content.length / 2;
        DeferredContentProvider contentProvider = new DeferredContentProvider();
        contentProvider.offer(ByteBuffer.wrap(content, 0, chunk1));
        CountDownLatch clientLatch = new CountDownLatch(1);
        client.newRequest("localhost", serverConnector.getLocalPort())
                .header(HttpHeader.EXPECT, HttpHeaderValue.CONTINUE.asString())
                .content(contentProvider)
                .send(result ->
                {
                    if (result.isFailed())
                        clientLatch.countDown();
                });

        // Wait more than the idle timeout to break the connection.
        Thread.sleep(2 * idleTimeout);

        Assert.assertTrue(serverLatch.await(555, TimeUnit.SECONDS));
        Assert.assertTrue(clientLatch.await(555, TimeUnit.SECONDS));
    }
