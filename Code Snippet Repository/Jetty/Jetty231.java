    @Override
    public String toString()
    {
        int activeSize;
        int idleSize;
        lock();
        try
        {
            activeSize = activeConnections.size();
            idleSize = idleConnections.size();
        }
        finally
        {
            unlock();
        }

        return String.format("%s@%x[c=%d/%d,a=%d,i=%d]",
                getClass().getSimpleName(),
                hashCode(),
                getConnectionCount(),
                getMaxConnectionCount(),
                activeSize,
                idleSize);
    }
