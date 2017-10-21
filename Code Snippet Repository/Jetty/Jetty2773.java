    @Override
    public synchronized void onClosed(Connection connection)
    {
        if (LOG.isDebugEnabled())
            LOG.debug("onClosed {} < {} {}",_connections, _maxConnections, connection);
        if ( --_connections < _maxConnections && !_accepting)
        {
            _accepting = true;
            LOG.info("Connection Limit({}) cleared for {}",_maxConnections,_connectors);
            for (AbstractConnector c : _connectors)
                c.setAccepting(true);
        }
    }
