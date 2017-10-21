    @Test
    public void testRequestAbortSendsResetFrame() throws Exception
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
            client.newRequest("localhost", connector.getLocalPort())
                    .onRequestCommit(request -> request.abort(new Exception("explicitly_aborted_by_test")))
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            Assert.assertTrue(resetLatch.await(5, TimeUnit.SECONDS));
        }
    }
