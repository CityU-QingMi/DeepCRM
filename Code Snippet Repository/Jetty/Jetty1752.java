    @Test
    public void testAcquireReleaseClear() throws Exception
    {
        MappedByteBufferPool bufferPool = new MappedByteBufferPool();
        ConcurrentMap<Integer,Bucket> buckets = bufferPool.bucketsFor(true);

        ByteBuffer buffer = bufferPool.acquire(512, true);
        bufferPool.release(buffer);

        assertEquals(1, buckets.size());
        assertEquals(1, buckets.values().iterator().next().size());

        bufferPool.clear();

        assertTrue(buckets.isEmpty());
    }
