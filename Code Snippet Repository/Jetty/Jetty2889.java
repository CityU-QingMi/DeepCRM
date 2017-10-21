    protected void setLowResources()
    {
        for(Connector connector : getMonitoredOrServerConnectors())
        {
            if (connector instanceof AbstractConnector)
            {
                AbstractConnector c = (AbstractConnector)connector;
                if (c.isAccepting())
                {
                    _acceptingConnectors.add(c);
                    c.setAccepting(false);
                }
            }
            for (EndPoint endPoint : connector.getConnectedEndPoints())
                endPoint.setIdleTimeout(_lowResourcesIdleTimeout);
        }
    }
