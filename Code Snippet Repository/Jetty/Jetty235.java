    @Override
    protected Connection activate()
    {
        Connection connection;
        lock();
        try
        {
            connection = idleConnections.poll();
            if (connection == null)
                return null;
            activeConnections.add(connection);
        }
        finally
        {
            unlock();
        }

        return active(connection);
    }
