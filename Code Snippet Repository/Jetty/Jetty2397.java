    @Test
    public void testClientRequestReadFailsOnSecondRead() throws Exception
    {
        try (StacklessLogging scope = new StacklessLogging(HttpChannel.class))
        {
            startServer(new EchoHttpServlet());
            startProxy(new AsyncMiddleManServlet()
            {
                private int count;

                @Override
                protected int readClientRequestContent(ServletInputStream input, byte[] buffer) throws IOException
                {
                    if (++count < 2)
                        return super.readClientRequestContent(input, buffer);
                    else
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
                    if (result.getResponse().getStatus() == 502)
                        latch.countDown();
                }
            });
            content.offer(ByteBuffer.allocate(512));
            sleep(1000);
            content.offer(ByteBuffer.allocate(512));
            content.close();

            Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        }
    }
