    @Test
    public void testStreamSendingResetIsRemoved() throws Exception
    {
        start(new ServerSessionListener.Adapter());

        Session client = newClient(new Session.Listener.Adapter());
        MetaData.Request request = newRequest("GET", new HttpFields());
        HeadersFrame requestFrame = new HeadersFrame(request, null, false);
        FuturePromise<Stream> promise = new FuturePromise<>();
        client.newStream(requestFrame, promise, new Stream.Listener.Adapter());
        Stream stream = promise.get(5, TimeUnit.SECONDS);
        ResetFrame resetFrame = new ResetFrame(stream.getId(), ErrorCode.CANCEL_STREAM_ERROR.code);
        FutureCallback resetCallback = new FutureCallback();
        stream.reset(resetFrame, resetCallback);
        resetCallback.get(5, TimeUnit.SECONDS);
        // After reset the stream should be gone.
        Assert.assertEquals(0, client.getStreams().size());
    }
