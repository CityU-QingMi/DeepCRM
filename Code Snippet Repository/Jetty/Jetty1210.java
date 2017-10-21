    @Test
    public void testRequestIdleTimeoutSendsResetFrame() throws Exception
    {
        CountDownLatch resetLatch = new CountDownLatch(1);
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame)
            {
                return new Stream.Listener.Adapter()
                {
                    @Override
                    public void onReset(Stream stream, ResetFrame frame)
                    {
                        resetLatch.countDown();
                    }
                };
            }
        });

        try
        {
            long idleTimeout = 1000;
            client.newRequest("localhost", connector.getLocalPort())
                    .idleTimeout(idleTimeout, TimeUnit.MILLISECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException e)
        {
            // Expected.
        }

        Assert.assertTrue(resetLatch.await(5, TimeUnit.SECONDS));
    }
