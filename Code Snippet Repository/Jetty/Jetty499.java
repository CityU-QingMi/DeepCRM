    @Test
    public void testFailureBeforeRequestCommit() throws Exception
    {
        startServer(new EmptyServerHandler());

        final AtomicReference<HttpConnectionOverHTTP> connectionRef = new AtomicReference<>();
        client = new HttpClient(new HttpClientTransportOverHTTP()
        {
            @Override
            protected HttpConnectionOverHTTP newHttpConnection(EndPoint endPoint, HttpDestination destination, Promise<Connection> promise)
            {
                HttpConnectionOverHTTP connection = super.newHttpConnection(endPoint, destination, promise);
                connectionRef.set(connection);
                return connection;
            }
        }, null);
        client.start();

        try
        {
            client.newRequest("localhost", connector.getLocalPort())
                    .onRequestHeaders(request -> connectionRef.get().getEndPoint().close())
                    .timeout(5, TimeUnit.SECONDS)
                    .send();
            Assert.fail();
        }
        catch (ExecutionException x)
        {
            // Expected.
        }

        DuplexConnectionPool connectionPool = (DuplexConnectionPool)connectionRef.get().getHttpDestination().getConnectionPool();
        Assert.assertEquals(0, connectionPool.getConnectionCount());
        Assert.assertEquals(0, connectionPool.getActiveConnections().size());
        Assert.assertEquals(0, connectionPool.getIdleConnections().size());
    }
