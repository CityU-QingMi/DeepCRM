    public ConnectionLimit(int maxConnections, Connector...connectors)
    {
        _maxConnections = maxConnections;
        _server = null;
        for (Connector c: connectors)
        {
            if (c instanceof AbstractConnector)
                _connectors.add((AbstractConnector)c);
            else
                LOG.warn("Connector {} is not an AbstractConnection. Connections not limited",c);
        }
    }
