    @Override
    public void bind(InetSocketAddress addr, int backlog) throws IOException
    {
        // check if there is already a connector listening
        Collection<NetworkConnector> connectors = _server.getBeans(NetworkConnector.class);
        if (connectors != null)
        {
            for (NetworkConnector connector : connectors)
            {
                if (connector.getPort() == addr.getPort()) {
                    if (LOG.isDebugEnabled()) LOG.debug("server already bound to port " + addr.getPort() + ", no need to rebind");
                    return;
                }
            }
        }

        if (_serverShared)
            throw new IOException("jetty server is not bound to port " + addr.getPort());

        this._addr = addr;

        if (LOG.isDebugEnabled()) LOG.debug("binding server to port " + addr.getPort());
        ServerConnector connector = new ServerConnector(_server);
        connector.setPort(addr.getPort());
        connector.setHost(addr.getHostName());
        _server.addConnector(connector);

        _connectors.put(addr.getHostName() + addr.getPort(), connector);
    }
