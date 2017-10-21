    @Test
    public void test_SecondAcquire_AfterFirstAcquire_WithEmptyQueue_ReturnsSameConnection() throws Exception
    {
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

            Connection connection2 = connectionPool.acquire();
            Assert.assertSame(connection1, connection2);
        }
    }
