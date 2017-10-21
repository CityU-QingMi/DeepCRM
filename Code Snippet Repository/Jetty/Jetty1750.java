    @Test
    public void testAcquireRelease() throws Exception
    {
        MappedByteBufferPool bufferPool = new MappedByteBufferPool();
        ConcurrentMap<Integer,Bucket> buckets = bufferPool.bucketsFor(true);

        int size = 512;
        ByteBuffer buffer = bufferPool.acquire(size, true);

        assertTrue(buffer.isDirect());
        assertThat(buffer.capacity(), greaterThanOrEqualTo(size));
        assertTrue(buckets.isEmpty());

        bufferPool.release(buffer);

        assertEquals(1, buckets.size());
        assertEquals(1, buckets.values().iterator().next().size());
    }
