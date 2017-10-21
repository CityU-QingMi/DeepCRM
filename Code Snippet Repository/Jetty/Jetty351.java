    @Override
    public void dump(Appendable out, String indent) throws IOException
    {
        List<Holder> connections = new ArrayList<>();
        lock();
        try
        {
            connections.addAll(busyConnections.values());
            connections.addAll(muxedConnections.values());
            connections.addAll(idleConnections);
        }
        finally
        {
            unlock();
        }

        ContainerLifeCycle.dumpObject(out, this);
        ContainerLifeCycle.dump(out, indent, connections);
    }
