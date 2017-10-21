    @Test
    public void testMaxQueue() throws Exception
    {
        MappedByteBufferPool bufferPool = new MappedByteBufferPool(-1,2);
        ConcurrentMap<Integer,Bucket> buckets = bufferPool.bucketsFor(false);

        ByteBuffer buffer1 = bufferPool.acquire(512, false);
        ByteBuffer buffer2 = bufferPool.acquire(512, false);
        ByteBuffer buffer3 = bufferPool.acquire(512, false);
        assertEquals(0, buckets.size());

        bufferPool.release(buffer1);
        assertEquals(1, buckets.size());
        Bucket bucket=buckets.values().iterator().next();
        assertEquals(1, bucket.size());

        bufferPool.release(buffer2);
        assertEquals(2, bucket.size());
        
        bufferPool.release(buffer3);
        assertEquals(2, bucket.size());

    }
