    @Test
    public void testServerSendsBigContent() throws Exception
    {
        final byte[] data = new byte[1024 * 1024];
        new Random().nextBytes(data);

        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame requestFrame)
            {
                MetaData.Response metaData = new MetaData.Response(HttpVersion.HTTP_2, 200, new HttpFields());
                HeadersFrame responseFrame = new HeadersFrame(stream.getId(), metaData, null, false);
                Callback.Completable completable = new Callback.Completable();
                stream.headers(responseFrame, completable);
                completable.thenRun(() ->
                {
                    DataFrame dataFrame = new DataFrame(stream.getId(), ByteBuffer.wrap(data), true);
                    stream.data(dataFrame, Callback.NOOP);
                });
                return null;
            }
        });

        Session session = newClient(new Session.Listener.Adapter());
        MetaData.Request metaData = newRequest("GET", new HttpFields());
        HeadersFrame requestFrame = new HeadersFrame(metaData, null, true);
        final byte[] bytes = new byte[data.length];
        final CountDownLatch latch = new CountDownLatch(1);
        session.newStream(requestFrame, new Promise.Adapter<>(), new Stream.Listener.Adapter()
        {
            private int received;

            @Override
            public void onData(Stream stream, DataFrame frame, Callback callback)
            {
                int remaining = frame.remaining();
                frame.getData().get(bytes, received, remaining);
                this.received += remaining;
                callback.succeeded();
                if (frame.isEndStream())
                    latch.countDown();
            }
        });

        Assert.assertTrue(latch.await(15, TimeUnit.SECONDS));
        Assert.assertArrayEquals(data, bytes);
    }
