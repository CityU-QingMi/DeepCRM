    @Test
    public void testMaxQueue() throws Exception
    {
        ArrayByteBufferPool bufferPool = new ArrayByteBufferPool(-1,-1,-1,2);

        ByteBuffer buffer1 = bufferPool.acquire(512, false);
        ByteBuffer buffer2 = bufferPool.acquire(512, false);
        ByteBuffer buffer3 = bufferPool.acquire(512, false);

        Bucket[] buckets = bufferPool.bucketsFor(false);
        Arrays.asList(buckets).forEach(b->assertEquals(0,b.size()));
        
        bufferPool.release(buffer1);
        Bucket bucket=Arrays.asList(buckets).stream().filter(b->b.size()>0).findFirst().get();
        assertEquals(1, bucket.size());

        bufferPool.release(buffer2);
        assertEquals(2, bucket.size());
        
        bufferPool.release(buffer3);
        assertEquals(2, bucket.size());
    }
