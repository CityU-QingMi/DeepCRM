    @Override
    protected void onCreated(Connection connection)
    {
        lock();
        try
        {
            // Use "cold" new connections as last.
            idleConnections.offer(connection);
        }
        finally
        {
            unlock();
        }

        idle(connection, false);
    }
