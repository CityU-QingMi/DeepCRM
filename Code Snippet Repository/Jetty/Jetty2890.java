    protected void clearLowResources()
    {
        for(Connector connector : getMonitoredOrServerConnectors())
        {
            for (EndPoint endPoint : connector.getConnectedEndPoints())
                endPoint.setIdleTimeout(connector.getIdleTimeout());
        }
        
        for (AbstractConnector connector : _acceptingConnectors)
        {
            connector.setAccepting(true);
        }
        _acceptingConnectors.clear();
    }
