    @Test
    public void testFailedSessionClosesIdleStream() throws Exception
    {
        AtomicReference<Session> sessionRef = new AtomicReference<>();
        final CountDownLatch latch = new CountDownLatch(1);
        final List<Stream> streams = new ArrayList<>();
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame)
            {
                streams.add(stream);
                MetaData.Request request = (MetaData.Request)frame.getMetaData();
                if ("GET".equals(request.getMethod()))
                {
                    ((HTTP2Session)stream.getSession()).getEndPoint().close();
                    // Try to write something to force an error.
                    stream.data(new DataFrame(stream.getId(), ByteBuffer.allocate(1024), true), Callback.NOOP);
                }
                return null;
            }

            @Override
            public void onFailure(Session session, Throwable failure)
            {
                sessionRef.set(session);
                latch.countDown();
            }
        });

        Session session = newClient(new Session.Listener.Adapter());

        // First stream will be idle on server.
        HeadersFrame request1 = new HeadersFrame(newRequest("HEAD", new HttpFields()), null, true);
        session.newStream(request1, new Promise.Adapter<>(), new Stream.Listener.Adapter());

        // Second stream will fail on server.
        HeadersFrame request2 = new HeadersFrame(newRequest("GET", new HttpFields()), null, true);
        session.newStream(request2, new Promise.Adapter<>(), new Stream.Listener.Adapter());

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        Session serverSession = sessionRef.get();

        // Wait for the server to finish the close activities.
        Thread.sleep(1000);

        Assert.assertEquals(0, serverSession.getStreams().size());
        for (Stream stream : streams)
            Assert.assertTrue(stream.isClosed());
    }
