    @Override
    public ByteBuffer acquire(int size, boolean direct)
    {
        int b = bucketFor(size);
        ConcurrentMap<Integer, Bucket> buffers = bucketsFor(direct);

        Bucket bucket = buffers.get(b);
        if (bucket==null)
            return newByteBuffer(b*_factor, direct);
        return bucket.acquire(direct);
    }
