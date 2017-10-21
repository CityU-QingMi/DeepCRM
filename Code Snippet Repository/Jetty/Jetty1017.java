    @Test
    public void testClientResetConsumesQueuedData() throws Exception
    {
        start(new EmptyHttpServlet());

        Session client = newClient(new Session.Listener.Adapter());
        MetaData.Request request = newRequest("GET", new HttpFields());
        HeadersFrame frame = new HeadersFrame(request, null, false);
        FuturePromise<Stream> promise = new FuturePromise<>();
        client.newStream(frame, promise, new Stream.Listener.Adapter());
        Stream stream = promise.get(5, TimeUnit.SECONDS);
        ByteBuffer data = ByteBuffer.allocate(FlowControlStrategy.DEFAULT_WINDOW_SIZE);
        CountDownLatch dataLatch = new CountDownLatch(1);
        stream.data(new DataFrame(stream.getId(), data, false), new Callback()
        {
            @Override
            public void succeeded()
            {
                dataLatch.countDown();
            }
        });
        // The server does not read the data, so the flow control window should be zero.
        Assert.assertTrue(dataLatch.await(5, TimeUnit.SECONDS));
        Assert.assertEquals(0, ((ISession)client).updateSendWindow(0));

        // Now reset the stream.
        stream.reset(new ResetFrame(stream.getId(), ErrorCode.CANCEL_STREAM_ERROR.code), Callback.NOOP);

        // Wait for the server to receive the reset and process
        // it, and for the client to process the window updates.
        Thread.sleep(1000);

        Assert.assertThat(((ISession)client).updateSendWindow(0), Matchers.greaterThan(0));
    }
