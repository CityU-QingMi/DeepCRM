    @Test
    public void testMaxRelease() throws Exception
    {
        ArrayByteBufferPool bufferPool = new ArrayByteBufferPool(10,100,1000);
        ByteBufferPool.Bucket[] buckets = bufferPool.bucketsFor(true);

        for (int size=999;size<=1001;size++)
        {
            bufferPool.clear();
            ByteBuffer buffer = bufferPool.acquire(size, true);

            assertTrue(buffer.isDirect());
            assertThat(buffer.capacity(),greaterThanOrEqualTo(size));
            for (ByteBufferPool.Bucket bucket : buckets)
                assertTrue(bucket.isEmpty());

            bufferPool.release(buffer);

            int pooled=0;
            for (ByteBufferPool.Bucket bucket : buckets)
            {
                pooled+=bucket.size();
            }
            assertEquals(size<=1000,1==pooled);
        }
    }
