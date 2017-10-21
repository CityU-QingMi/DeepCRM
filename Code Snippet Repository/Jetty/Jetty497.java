    @Test
    public void testExplicitConnectionResponseListeners() throws Exception
    {
        start(new EmptyServerHandler());

        Destination destination = client.getDestination(scheme, "localhost", connector.getLocalPort());
        FuturePromise<Connection> futureConnection = new FuturePromise<>();
        destination.newConnection(futureConnection);
        Connection connection = futureConnection.get(5, TimeUnit.SECONDS);
        CountDownLatch responseLatch = new CountDownLatch(1);
        Request request = client.newRequest(destination.getHost(), destination.getPort())
                .scheme(scheme)
                .onResponseSuccess(response -> responseLatch.countDown());

        FutureResponseListener listener = new FutureResponseListener(request);
        connection.send(request, listener);
        ContentResponse response = listener.get(5, TimeUnit.SECONDS);

        Assert.assertEquals(HttpStatus.OK_200, response.getStatus());
        Assert.assertTrue(responseLatch.await(5, TimeUnit.SECONDS));
    }
