    @Test
    public void testRequestClosedResponseClosedClosesStream() throws Exception
    {
        final CountDownLatch latch = new CountDownLatch(2);
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(final Stream stream, HeadersFrame frame)
            {
                MetaData.Response metaData = new MetaData.Response(HttpVersion.HTTP_2, 200, new HttpFields());
                HeadersFrame response = new HeadersFrame(stream.getId(), metaData, null, true);
                stream.headers(response, new Callback()
                {
                    @Override
                    public void succeeded()
                    {
                        Assert.assertTrue(stream.isClosed());
                        Assert.assertEquals(0, stream.getSession().getStreams().size());
                        latch.countDown();
                    }
                });
                return null;
            }
        });

        Session session = newClient(new Session.Listener.Adapter());
        HeadersFrame frame = new HeadersFrame(newRequest("GET", new HttpFields()), null, true);
        FuturePromise<Stream> promise = new FuturePromise<>();
        session.newStream(frame, promise, new Stream.Listener.Adapter()
        {
            @Override
            public void onHeaders(Stream stream, HeadersFrame frame)
            {
                // The stream promise may not be notified yet here.
                latch.countDown();
            }
        });
        Stream stream = promise.get(5, TimeUnit.SECONDS);
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        Assert.assertTrue(stream.isClosed());
    }
