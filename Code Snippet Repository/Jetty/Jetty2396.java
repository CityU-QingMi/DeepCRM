    @Test
    public void testClientRequestReadFailsOnFirstRead() throws Exception
    {
        startServer(new EchoHttpServlet());
        startProxy(new AsyncMiddleManServlet()
        {
            @Override
            protected int readClientRequestContent(ServletInputStream input, byte[] buffer) throws IOException
            {
                throw new IOException("explicitly_thrown_by_test");
            }
        });
        startClient();

        final CountDownLatch latch = new CountDownLatch(1);
        DeferredContentProvider content = new DeferredContentProvider();
        client.newRequest("localhost", serverConnector.getLocalPort())
                .content(content)
                .send(new Response.CompleteListener()
                {
                    @Override
                    public void onComplete(Result result)
                    {
                        System.err.println(result);
                        if (result.getResponse().getStatus() == 500)
                            latch.countDown();
                    }
                });
        content.offer(ByteBuffer.allocate(512));
        sleep(1000);
        content.offer(ByteBuffer.allocate(512));
        content.close();

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
