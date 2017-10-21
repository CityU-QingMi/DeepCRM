    @Test
    public void testStartAsyncThenClientStreamIdleTimeout() throws Exception
    {
        CountDownLatch serverLatch = new CountDownLatch(1);
        start(new AsyncOnErrorServlet(serverLatch));
        long idleTimeout = 1000;
        client.setIdleTimeout(10 * idleTimeout);

        Session session = newClient(new Session.Listener.Adapter());
        HttpFields fields = new HttpFields();
        MetaData.Request metaData = newRequest("GET", fields);
        HeadersFrame frame = new HeadersFrame(metaData, null, true);
        FuturePromise<Stream> promise = new FuturePromise<>();
        CountDownLatch clientLatch = new CountDownLatch(1);
        session.newStream(frame, promise, new Stream.Listener.Adapter()
        {
            @Override
            public boolean onIdleTimeout(Stream stream, Throwable x)
            {
                clientLatch.countDown();
                return true;
            }
        });
        Stream stream = promise.get(5, TimeUnit.SECONDS);
        stream.setIdleTimeout(idleTimeout);

        // When the client resets, the server receives the
        // corresponding frame and acts by notifying the failure,
        // but the response is not sent back to the client.
        Assert.assertTrue(serverLatch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));
        Assert.assertTrue(clientLatch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));
    }
