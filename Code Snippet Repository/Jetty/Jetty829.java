    public void start() throws Exception
    {
        Assert.assertNotNull("Server should not be null (failed load?)",_server);

        _server.start();

        // Find the active server port.
        _serverPort = -1;
        Connector connectors[] = _server.getConnectors();
        for (int i = 0; _serverPort<0 && i < connectors.length; i++)
        {
            if (connectors[i] instanceof NetworkConnector)
            {
                int port = ((NetworkConnector)connectors[i]).getLocalPort();
                if (port>0)
                    _serverPort=port;
            }
        }

        Assert.assertTrue("Server Port is between 1 and 65535. Actually <" + _serverPort + ">",(1 <= this._serverPort) && (this._serverPort <= 65535));

        // Uncomment to have server start and continue to run (without exiting)
        // System.err.printf("Listening to port %d%n",this.serverPort);
        // server.join();
    }
