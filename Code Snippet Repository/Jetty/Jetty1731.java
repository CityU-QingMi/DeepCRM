    @Test
    public void testAcquireRelease() throws Exception
    {
        ArrayByteBufferPool bufferPool = new ArrayByteBufferPool(10,100,1000);
        ByteBufferPool.Bucket[] buckets = bufferPool.bucketsFor(true);

        for (int size=390;size<=510;size++)
        {
            bufferPool.clear();
            ByteBuffer buffer = bufferPool.acquire(size, true);

            assertTrue(buffer.isDirect());
            assertThat(buffer.capacity(), greaterThanOrEqualTo(size));
            for (ByteBufferPool.Bucket bucket : buckets)
                assertTrue(bucket.isEmpty());

            bufferPool.release(buffer);

            int pooled=0;
            for (ByteBufferPool.Bucket bucket : buckets)
            {
                if (!bucket.isEmpty())
                {
                    pooled+=bucket.size();
                    // TODO assertThat(bucket._bufferSize,greaterThanOrEqualTo(size));
                    // TODO assertThat(bucket._bufferSize,Matchers.lessThan(size+100));
                }
            }
            assertEquals(1,pooled);
        }
    }
