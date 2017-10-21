    private ByteBufferPool.Bucket bucketFor(int size,boolean direct)
    {
        if (size<=_min)
            return null;
        int b=(size-1)/_inc;
        if (b>=_direct.length)
            return null;
        ByteBufferPool.Bucket bucket = direct?_direct[b]:_indirect[b];
                
        return bucket;
    }
