    @Test
    public void testIterative() throws Exception
    {
        start(new LoadHandler());

        client.setByteBufferPool(new LeakTrackingByteBufferPool(new MappedByteBufferPool.Tagged()));
        client.setMaxConnectionsPerDestination(32768);
        client.setMaxRequestsQueuedPerDestination(1024 * 1024);

        // At least 25k requests to warmup properly (use -XX:+PrintCompilation to verify JIT activity)
        int runs = 1;
        int iterations = 500;
        for (int i = 0; i < runs; ++i)
            run(iterations);

        // Re-run after warmup
        iterations = 5_000;
        for (int i = 0; i < runs; ++i)
            run(iterations);

        System.gc();

        ByteBufferPool byteBufferPool = connector.getByteBufferPool();
        if (byteBufferPool instanceof LeakTrackingByteBufferPool)
        {
            LeakTrackingByteBufferPool serverBufferPool = (LeakTrackingByteBufferPool)byteBufferPool;
            assertThat("Server BufferPool - leaked acquires", serverBufferPool.getLeakedAcquires(), Matchers.is(0L));
            assertThat("Server BufferPool - leaked releases", serverBufferPool.getLeakedReleases(), Matchers.is(0L));
            assertThat("Server BufferPool - unreleased", serverBufferPool.getLeakedResources(), Matchers.is(0L));
        }

        byteBufferPool = client.getByteBufferPool();
        if (byteBufferPool instanceof LeakTrackingByteBufferPool)
        {
            LeakTrackingByteBufferPool clientBufferPool = (LeakTrackingByteBufferPool)byteBufferPool;
            assertThat("Client BufferPool - leaked acquires", clientBufferPool.getLeakedAcquires(), Matchers.is(0L));
            assertThat("Client BufferPool - leaked releases", clientBufferPool.getLeakedReleases(), Matchers.is(0L));
            assertThat("Client BufferPool - unreleased", clientBufferPool.getLeakedResources(), Matchers.is(0L));
        }

        assertThat("Connection Leaks", connectionLeaks.get(), Matchers.is(0L));
    }
