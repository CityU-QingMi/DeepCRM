    @Override
    public synchronized void onOpened(Connection connection)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("onOpen {} < {} {}",_connections, _maxConnections, connection);
        if ( ++_connections >= _maxConnections && _accepting)
        {
            _accepting = false;
            LOG.info("Connection Limit({}) reached for {}",_maxConnections,_connectors);
            for (AbstractConnector c : _connectors)
                c.setAccepting(false);
        }
    }
