    @Test
    public void testServerEnforcingStreamIdleTimeout() throws Exception
    {
        final CountDownLatch timeoutLatch = new CountDownLatch(1);
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame)
            {
                stream.setIdleTimeout(idleTimeout);
                return new Stream.Listener.Adapter()
                {
                    @Override
                    public boolean onIdleTimeout(Stream stream, Throwable x)
                    {
                        timeoutLatch.countDown();
                        return true;
                    }
                };
            }
        });

        final CountDownLatch resetLatch = new CountDownLatch(1);
        Session session = newClient(new Session.Listener.Adapter());
        MetaData.Request metaData = newRequest("GET", new HttpFields());
        // Stream does not end here, but we won't send any DATA frame.
        HeadersFrame requestFrame = new HeadersFrame(metaData, null, false);
        session.newStream(requestFrame, new Promise.Adapter<>(), new Stream.Listener.Adapter()
        {
            @Override
            public void onReset(Stream stream, ResetFrame frame)
            {
                resetLatch.countDown();
            }
        });

        Assert.assertTrue(timeoutLatch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(resetLatch.await(5, TimeUnit.SECONDS));
        // Stream must be gone.
        Assert.assertTrue(session.getStreams().isEmpty());
        // Session must not be closed, nor disconnected.
        Assert.assertFalse(session.isClosed());
        Assert.assertFalse(((HTTP2Session)session).isDisconnected());
    }
