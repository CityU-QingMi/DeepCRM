    @Test
    public void testAcquireReleaseAcquire() throws Exception
    {
        ArrayByteBufferPool bufferPool = new ArrayByteBufferPool(10,100,1000);
        ByteBufferPool.Bucket[] buckets = bufferPool.bucketsFor(true);

        for (int size=390;size<=510;size++)
        {
            bufferPool.clear();
            ByteBuffer buffer1 = bufferPool.acquire(size, true);
            bufferPool.release(buffer1);
            ByteBuffer buffer2 = bufferPool.acquire(size, true);
            bufferPool.release(buffer2);
            ByteBuffer buffer3 = bufferPool.acquire(size, false);
            bufferPool.release(buffer3);

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

            assertTrue(buffer1==buffer2);
            assertTrue(buffer1!=buffer3);
        }
    }
