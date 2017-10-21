    public void disconnect()
    {
        IO.close(serverConnector);

        if (connectorServer != null)
        {
            try
            {
                connectorServer.stop();
            }
            catch (Exception ignore)
            {
                /* ignore */
            }
        }
    }
