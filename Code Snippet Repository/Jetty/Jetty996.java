    @Test
    public void testPriorityBeforeHeaders() throws Exception
    {
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame)
            {
                MetaData.Response metaData = new MetaData.Response(HttpVersion.HTTP_2, 200, new HttpFields());
                HeadersFrame responseFrame = new HeadersFrame(stream.getId(), metaData, null, true);
                stream.headers(responseFrame, Callback.NOOP);
                return null;
            }
        });

        Session session = newClient(new Session.Listener.Adapter());
        int streamId = session.priority(new PriorityFrame(0, 13, false), Callback.NOOP);
        Assert.assertTrue(streamId > 0);

        CountDownLatch latch = new CountDownLatch(2);
        MetaData metaData = newRequest("GET", new HttpFields());
        HeadersFrame headersFrame = new HeadersFrame(streamId, metaData, null, true);
        session.newStream(headersFrame, new Promise.Adapter<Stream>()
        {
            @Override
            public void succeeded(Stream result)
            {
                Assert.assertEquals(streamId, result.getId());
                latch.countDown();
            }
        }, new Stream.Listener.Adapter()
        {
            @Override
            public void onHeaders(Stream stream, HeadersFrame frame)
            {
                if (frame.isEndStream())
                    latch.countDown();
            }
        });

        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
    }
