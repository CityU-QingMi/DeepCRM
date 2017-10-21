    @Test
    public void test_Acquire_Process_Release_Acquire_ReturnsSameConnection() throws Exception
    {
        HttpDestinationOverHTTP destination = new HttpDestinationOverHTTP(client, new Origin("http", "localhost", connector.getLocalPort()));
        destination.start();
        DuplexConnectionPool connectionPool = (DuplexConnectionPool)destination.getConnectionPool();
        HttpConnectionOverHTTP connection1 = (HttpConnectionOverHTTP)connectionPool.acquire();

        long start = System.nanoTime();
        while (connection1 == null && TimeUnit.NANOSECONDS.toSeconds(System.nanoTime() - start) < 5)
        {
            TimeUnit.MILLISECONDS.sleep(50);
            connection1 = (HttpConnectionOverHTTP)connectionPool.getIdleConnections().peek();
        }
        Assert.assertNotNull(connection1);

        // Acquire the connection to make it active
        Assert.assertSame(connection1, connectionPool.acquire());

        destination.process(connection1);
        destination.release(connection1);

        Connection connection2 = connectionPool.acquire();
        Assert.assertSame(connection1, connection2);
    }
