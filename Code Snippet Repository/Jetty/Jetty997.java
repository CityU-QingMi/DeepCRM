    @Test
    public void testHeadersWithPriority() throws Exception
    {
        PriorityFrame priorityFrame = new PriorityFrame(13, 200, true);
        CountDownLatch latch = new CountDownLatch(2);
        start(new ServerSessionListener.Adapter()
        {
            @Override
            public Stream.Listener onNewStream(Stream stream, HeadersFrame frame)
            {
                PriorityFrame priority = frame.getPriority();
                Assert.assertNotNull(priority);
                Assert.assertEquals(priorityFrame.getParentStreamId(), priority.getParentStreamId());
                Assert.assertEquals(priorityFrame.getWeight(), priority.getWeight());
                Assert.assertEquals(priorityFrame.isExclusive(), priority.isExclusive());
                latch.countDown();

                MetaData.Response metaData = new MetaData.Response(HttpVersion.HTTP_2, 200, new HttpFields());
                HeadersFrame responseFrame = new HeadersFrame(stream.getId(), metaData, null, true);
                stream.headers(responseFrame, Callback.NOOP);
                return null;
            }
        });

        Session session = newClient(new Session.Listener.Adapter());
        MetaData metaData = newRequest("GET", "/one", new HttpFields());
        HeadersFrame headersFrame = new HeadersFrame(metaData, priorityFrame, true);
        session.newStream(headersFrame, new Promise.Adapter<>(), new Stream.Listener.Adapter()
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
