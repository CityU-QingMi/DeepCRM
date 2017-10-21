    public void start() throws Exception
    {
        Assert.assertNotNull("Server should not be null (failed load?)",_server);

        _server.start();

        // Find the active server port.
        this._serverPort = ((NetworkConnector)_server.getConnectors()[0]).getLocalPort();
        Assert.assertTrue("Server Port is between 1 and 65535. Actually <" + _serverPort + ">",(1 <= this._serverPort) && (this._serverPort <= 65535));
    }
