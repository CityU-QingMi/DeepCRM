    @Test
    public void testStreamReceivingResetIsRemoved() throws Exception
    {
        final AtomicReference<Stream> streamRef = new AtomicReference<>();
        final CountDownLatch resetLatch = new CountDownLatch(1);
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
                        Assert.assertNotNull(stream);
                        Assert.assertTrue(stream.isReset());
                        streamRef.set(stream);
                        resetLatch.countDown();
                    }
                };
            }
        });

        Session client = newClient(new Session.Listener.Adapter());
        MetaData.Request request = newRequest("GET", new HttpFields());
        HeadersFrame requestFrame = new HeadersFrame(request, null, false);
        FuturePromise<Stream> promise = new FuturePromise<>();
        client.newStream(requestFrame, promise, new Stream.Listener.Adapter());
        Stream stream = promise.get(5, TimeUnit.SECONDS);
        ResetFrame resetFrame = new ResetFrame(stream.getId(), ErrorCode.CANCEL_STREAM_ERROR.code);
        stream.reset(resetFrame, Callback.NOOP);

        Assert.assertTrue(resetLatch.await(5, TimeUnit.SECONDS));

        // Wait a while to let the server remove the
        // stream after returning from onReset().
        Thread.sleep(1000);

        Stream serverStream = streamRef.get();
        Assert.assertEquals(0, serverStream.getSession().getStreams().size());
    }
