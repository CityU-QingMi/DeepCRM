    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        List<Connection> connections = new ArrayList<>();
        lock();
        try
        {
            connections.addAll(activeConnections);
            connections.addAll(idleConnections);
        }
        finally
        {
            unlock();
        }

        ContainerLifeCycle.dumpObject(out, this);
        ContainerLifeCycle.dump(out, indent, connections);
    }
