    public long getFirst(long size)
    {
        if (first<0)
        {
            long tf=size-last;
            if (tf<0)
                tf=0;
            return tf;
        }
        return first;
    }
