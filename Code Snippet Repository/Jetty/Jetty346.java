    @Override
    public boolean isActive(Connection connection)
    {
        lock();
        try
        {
            if (muxedConnections.containsKey(connection))
                return true;
            if (busyConnections.containsKey(connection))
                return true;
            return false;
        }
        finally
        {
            unlock();
        }
    }
