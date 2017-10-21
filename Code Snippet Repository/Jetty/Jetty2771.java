    @Override
    protected synchronized void doStart() throws Exception
    {
        if (_server!=null)
        {
            for (Connector c: _server.getConnectors())
            {
                if (c instanceof AbstractConnector)
                    _connectors.add((AbstractConnector)c);
                else
                    LOG.warn("Connector {} is not an AbstractConnection. Connections not limited",c);
            }
        }

        if (LOG.isDebugEnabled())
            LOG.debug("ConnectionLimit {} for {}",_maxConnections,_connectors);
        
        _connections = 0;
        _accepting = true;
        
        for (AbstractConnector c : _connectors)
            c.addBean(this);
    }
