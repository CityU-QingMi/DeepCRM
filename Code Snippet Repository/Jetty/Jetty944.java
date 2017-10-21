    private void cleanUpConnectors()
    {
        for (Map.Entry<String, Connector> stringConnectorEntry : _connectors.entrySet())
        {
            Connector connector = stringConnectorEntry.getValue();
            try
            {
                connector.stop();
            } 
            catch (Exception ex) 
            {
                LOG.warn(ex);
            }
            _server.removeConnector(connector);
        }
        _connectors.clear();
    }
