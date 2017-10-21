    @ManagedAttribute(value = "", readonly = true)
    public int getIdleConnectionCount()
    {
        lock();
        try
        {
            return idleConnections.size();
        }
        finally
        {
            unlock();
        }
    }
