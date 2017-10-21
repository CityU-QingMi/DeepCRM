    @Override
    protected void onCreated(Connection connection)
    {
        lock();
        try
        {
            // Use "cold" connections as last.
            idleConnections.offer(new Holder(connection));
        }
        finally
        {
            unlock();
        }

        idle(connection, false);
    }
