    @Override
    public boolean isActive(Connection connection)
    {
        lock();
        try
        {
            return activeConnections.contains(connection);
        }
        finally
        {
            unlock();
        }
    }
