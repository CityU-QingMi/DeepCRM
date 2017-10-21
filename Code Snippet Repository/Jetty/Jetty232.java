    @ManagedAttribute(value = "", readonly = true)
    public int getActiveConnectionCount()
    {
        lock();
        try
        {
            return activeConnections.size();
        }
        finally
        {
            unlock();
        }
    }
