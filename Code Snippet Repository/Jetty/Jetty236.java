    public boolean release(Connection connection)
    {
        boolean closed = isClosed();
        lock();
        try
        {
            if (!activeConnections.remove(connection))
                return false;

            if (!closed)
            {
                // Make sure we use "hot" connections first.
                deactivate(connection);
            }
        }
        finally
        {
            unlock();
        }

        released(connection);
        return idle(connection, closed);
    }
