    @Override
    public void close()
    {
        super.close();

        List<Connection> connections;
        lock();
        try
        {
            connections = idleConnections.stream().map(holder -> holder.connection).collect(Collectors.toList());
            connections.addAll(muxedConnections.keySet());
            connections.addAll(busyConnections.keySet());
        }
        finally
        {
            unlock();
        }

        close(connections);
    }
