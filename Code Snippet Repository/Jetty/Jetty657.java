    @Test
    public void test_IdleConnection_IdleTimeout() throws Exception
    {
        long idleTimeout = 1000;
        client.setIdleTimeout(idleTimeout);

        HttpDestinationOverHTTP destination = new HttpDestinationOverHTTP(client, new Origin("http", "localhost", connector.getLocalPort()));
        destination.start();
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();
        Connection connection1 = connectionPool.acquire();
        if (connection1 == null)
        {
            // There are no queued requests, so the newly created connection will be idle
            long start = System.nanoTime();
            while (connection1 == null && TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start) < 5)
            {
                TimeUnit.MILLISECONDS.sleep(50);
                connection1 = connectionPool.getIdleConnections().peek();
            }
            Assert.assertNotNull(connection1);

            TimeUnit.MILLISECONDS.sleep(2 * idleTimeout);

            connection1 = connectionPool.getIdleConnections().poll();
            Assert.assertNull(connection1);
        }
    }
