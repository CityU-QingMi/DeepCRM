    public WebSocketUpgradeFilterTest(String testId, ServerProvider serverProvider) throws Exception
    {
        this.server = serverProvider.newServer();
        
        ServerConnector connector = (ServerConnector) server.getConnectors()[0];
        
        // Establish the Server URI
        String host = connector.getHost();
        if (host == null)
        {
            host = "localhost";
        }
        int port = connector.getLocalPort();
        
        serverUri = new URI(String.format("ws://%s:%d/", host, port));
    }
