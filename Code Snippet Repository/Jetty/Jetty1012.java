    @Test
    public void testRequestClosedRemotelyClosesStream() throws Exception
    {
        final CountDownLatch latch = new CountDownLatch(1);
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame)
            {
                Assert.assertTrue(((HTTP2Stream)stream).isRemotelyClosed());
                latch.countDown();
                return null;
            }
        });

        Session session = newClient(new Session.Listener.Adapter());
        HeadersFrame frame = new HeadersFrame(newRequest("GET", new HttpFields()), null, true);
        FuturePromise<Stream> promise = new FuturePromise<>();
        session.newStream(frame, promise, null);
        Stream stream = promise.get(5, TimeUnit.SECONDS);
        Assert.assertTrue(((HTTP2Stream)stream).isLocallyClosed());
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
