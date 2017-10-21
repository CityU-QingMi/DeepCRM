    @Test
    public void testConcurrent() throws Exception
    {
        start(new LoadHandler());

        client.setByteBufferPool(new LeakTrackingByteBufferPool(new MappedByteBufferPool.Tagged()));
        client.setMaxConnectionsPerDestination(32768);
        client.setMaxRequestsQueuedPerDestination(1024 * 1024);

        int runs = 1;
        int iterations = 256;
        IntStream.range(0, 16).parallel().forEach(i ->
                IntStream.range(0, runs).forEach(j ->
                        run(iterations)));
    }
