    @Override
    public void release(ByteBuffer buffer)
    {
        if (buffer == null)
            return; // nothing to do
        
        // validate that this buffer is from this pool
        assert((buffer.capacity() % _factor) == 0);
        
        int b = bucketFor(buffer.capacity());
        ConcurrentMap<Integer, Bucket> buckets = bucketsFor(buffer.isDirect());

        Bucket bucket = buckets.computeIfAbsent(b,_newBucket);
        bucket.release(buffer);
    }
