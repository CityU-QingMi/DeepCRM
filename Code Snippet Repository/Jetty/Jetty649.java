    @Test
    public void testRequestWithExplicitConnectionControl() throws Exception
    {
        HttpClient client = new HttpClient();
        client.start();

        // Create an explicit connection, and use try-with-resources to manage it
        FuturePromise<Connection> futureConnection = new FuturePromise<>();
        client.getDestination("http", "localhost", 8080).newConnection(futureConnection);
        try (Connection connection = futureConnection.get(5, TimeUnit.SECONDS))
        {
            Request request = client.newRequest("localhost", 8080);

            // Asynchronous send but using FutureResponseListener
            FutureResponseListener listener = new FutureResponseListener(request);
            connection.send(request, listener);
            // Wait for the response on the listener
            Response response = listener.get(5, TimeUnit.SECONDS);

            Assert.assertNotNull(response);
            Assert.assertEquals(200, response.getStatus());
        }
    }
