    public ArrayByteBufferPool(int minSize, int increment, int maxSize, int maxQueue)
    {
        if (minSize<=0)
            minSize=0;
        if (increment<=0)
            increment=1024;
        if (maxSize<=0)
            maxSize=64*1024;
        if (minSize>=increment)
            throw new IllegalArgumentException("minSize >= increment");
        if ((maxSize%increment)!=0 || increment>=maxSize)
            throw new IllegalArgumentException("increment must be a divisor of maxSize");
        _min=minSize;
        _inc=increment;

        _direct=new ByteBufferPool.Bucket[maxSize/increment];
        _indirect=new ByteBufferPool.Bucket[maxSize/increment];
        _maxQueue=maxQueue;

        int size=0;
        for (int i=0;i<_direct.length;i++)
        {
            size+=_inc;
            _direct[i]=new ByteBufferPool.Bucket(this,size,_maxQueue);
            _indirect[i]=new ByteBufferPool.Bucket(this,size,_maxQueue);
        }
    }
