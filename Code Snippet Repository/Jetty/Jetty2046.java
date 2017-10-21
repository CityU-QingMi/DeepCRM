    public void disconnect()
    {
        try
        {
            if (_serverConnector != null)
            {
                _serverConnector.close();
                _serviceConnection = null;
            }
            if (_connectorServer != null)
            {
                _connectorServer.stop();
                _connectorServer = null;
            }
        }
        catch (Exception ex)
        {
            LOG.debug(ex);
        }
    }
