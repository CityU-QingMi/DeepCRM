    @Test
    public void testMinimumRelease() throws Exception
    {
        ArrayByteBufferPool bufferPool = new ArrayByteBufferPool(10,100,1000);
        ByteBufferPool.Bucket[] buckets = bufferPool.bucketsFor(true);

        for (int size=1;size<=9;size++)
        {
            ByteBuffer buffer = bufferPool.acquire(size, true);

            assertTrue(buffer.isDirect());
            assertEquals(size,buffer.capacity());
            for (ByteBufferPool.Bucket bucket : buckets)
                assertTrue(bucket.isEmpty());

            bufferPool.release(buffer);

            for (ByteBufferPool.Bucket bucket : buckets)
                assertTrue(bucket.isEmpty());
        }
    }
