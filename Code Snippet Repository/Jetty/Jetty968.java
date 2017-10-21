    @Test
    public void testStartAsyncThenClientSessionIdleTimeout() throws Exception
    {
        CountDownLatch serverLatch = new CountDownLatch(1);
        start(new AsyncOnErrorServlet(serverLatch));
        long idleTimeout = 1000;
        client.setIdleTimeout(idleTimeout);

        Session session = newClient(new Session.Listener.Adapter());
        HttpFields fields = new HttpFields();
        MetaData.Request metaData = newRequest("GET", fields);
        HeadersFrame frame = new HeadersFrame(metaData, null, true);
        FuturePromise<Stream> promise = new FuturePromise<>();
        CountDownLatch clientLatch = new CountDownLatch(1);
        session.newStream(frame, promise, new Stream.Listener.Adapter()
        {
            @Override
            public void onHeaders(Stream stream, HeadersFrame frame)
            {
                MetaData.Response response = (MetaData.Response)frame.getMetaData();
                if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR_500 && frame.isEndStream())
                    clientLatch.countDown();
            }
        });
        Stream stream = promise.get(5, TimeUnit.SECONDS);
        stream.setIdleTimeout(10 * idleTimeout);

        // When the client closes, the server receives the
        // corresponding frame and acts by notifying the failure,
        // which sends back to the client the error response.
        Assert.assertTrue(serverLatch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));
        Assert.assertTrue(clientLatch.await(2 * idleTimeout, TimeUnit.MILLISECONDS));
    }
