    @Test
    public void testUpstreamTransformationThrowsAfterCommittingProxyRequest() throws Exception
    {
        try (StacklessLogging scope = new StacklessLogging(HttpChannel.class))
        {
            startServer(new EchoHttpServlet());
            startProxy(new AsyncMiddleManServlet()
            {
                @Override
                protected ContentTransformer newClientRequestContentTransformer(HttpServletRequest clientRequest, Request proxyRequest)
                {
                    return new ContentTransformer()
                    {
                        private int count;

                        @Override
                        public void transform(ByteBuffer input, boolean finished, List<ByteBuffer> output) throws IOException
                        {
                            if (++count < 2)
                                output.add(input);
                            else
                                throw new NullPointerException("explicitly_thrown_by_test");
                        }
                    };
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
                    if (result.isSucceeded() && result.getResponse().getStatus() == 502)
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
