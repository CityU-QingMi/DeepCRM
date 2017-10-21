    @Test
    public void testConnectionIdleTimeoutSendsResetFrame() throws Exception
    {
        long idleTimeout = 1000;

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
        client.stop();
        client.setIdleTimeout(idleTimeout);
        client.start();

        try
        {
            client.newRequest("localhost", connector.getLocalPort())
                    // Make sure the connection idle times out, not the stream.
                    .idleTimeout(2 * idleTimeout, TimeUnit.MILLISECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException e)
        {
            // Expected.
        }

        Assert.assertTrue(resetLatch.await(5, TimeUnit.SECONDS));
    }
