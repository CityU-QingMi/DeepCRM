    @Override
    public String toString()
    {
        int busySize;
        int muxedSize;
        int idleSize;
        lock();
        try
        {
            busySize = busyConnections.size();
            muxedSize = muxedConnections.size();
            idleSize = idleConnections.size();
        }
        finally
        {
            unlock();
        }
        return String.format("%s@%x[c=%d/%d,b=%d,m=%d,i=%d]",
                getClass().getSimpleName(),
                hashCode(),
                getConnectionCount(),
                getMaxConnectionCount(),
                busySize,
                muxedSize,
                idleSize);
    }
