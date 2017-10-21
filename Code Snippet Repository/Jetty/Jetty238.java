    public void close()
    {
        super.close();

        List<Connection> connections = new ArrayList<>();
        lock();
        try
        {
            connections.addAll(idleConnections);
            idleConnections.clear();
            connections.addAll(activeConnections);
            activeConnections.clear();
        }
        finally
        {
            unlock();
        }

        close(connections);
    }
