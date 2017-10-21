    private void testServerClosesConnectionAfterResponseWithQueuedRequestWithMaxConnections(Handler handler) throws Exception
    {
        start(handler);
        client.setMaxConnectionsPerDestination(1);

        final CountDownLatch latch = new CountDownLatch(1);
        Request request1 = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/one")
                .onRequestBegin(r ->
                {
                    try
                    {
                        latch.await();
                    }
                    catch (InterruptedException x)
                    {
                        r.abort(x);
                    }
                });
        FutureResponseListener listener1 = new FutureResponseListener(request1);
        request1.send(listener1);

        Request request2 = client.newRequest("localhost", connector.getLocalPort())
                .scheme(scheme)
                .path("/two");
        FutureResponseListener listener2 = new FutureResponseListener(request2);
        request2.send(listener2);

        // Now we have one request about to be sent, and one queued.

        latch.countDown();

        ContentResponse response1 = listener1.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(200, response1.getStatus());

        ContentResponse response2 = listener2.get(5, TimeUnit.SECONDS);
        Assert.assertEquals(200, response2.getStatus());
    }
