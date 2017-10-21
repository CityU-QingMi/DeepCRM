    @Test
    public void test_FirstAcquire_WithEmptyQueue() throws Exception
    {
        HttpDestinationOverHTTP destination = new HttpDestinationOverHTTP(client, new Origin("http", "localhost", connector.getLocalPort()));
        destination.start();
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();
        Connection connection = connectionPool.acquire();
        if (connection == null)
        {
            // There are no queued requests, so the newly created connection will be idle
            connection = timedPoll(connectionPool.getIdleConnections(), 5, TimeUnit.SECONDS);
        }
        Assert.assertNotNull(connection);
    }
