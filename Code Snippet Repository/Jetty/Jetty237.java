    protected boolean remove(Connection connection, boolean force)
    {
        boolean activeRemoved;
        boolean idleRemoved;
        lock();
        try
        {
            activeRemoved = activeConnections.remove(connection);
            idleRemoved = idleConnections.remove(connection);
        }
        finally
        {
            unlock();
        }

        if (activeRemoved || force)
            released(connection);
        boolean removed = activeRemoved || idleRemoved || force;
        if (removed)
            removed(connection);
        return removed;
    }
