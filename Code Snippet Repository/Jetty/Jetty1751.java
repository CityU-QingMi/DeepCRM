    @Test
    public void testAcquireReleaseAcquire() throws Exception
    {
        MappedByteBufferPool bufferPool = new MappedByteBufferPool();
        ConcurrentMap<Integer,Bucket> buckets = bufferPool.bucketsFor(false);

        ByteBuffer buffer1 = bufferPool.acquire(512, false);
        bufferPool.release(buffer1);
        ByteBuffer buffer2 = bufferPool.acquire(512, false);

        assertSame(buffer1, buffer2);
        assertEquals(1, buckets.size());
        assertEquals(0, buckets.values().iterator().next().size());

        bufferPool.release(buffer2);

        assertEquals(1, buckets.size());
        assertEquals(1, buckets.values().iterator().next().size());
    }
