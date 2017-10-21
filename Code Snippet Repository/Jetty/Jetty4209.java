    public String createConnector(boolean localhost) throws Exception
    {
        ServerConnector connector = new ServerConnector(_server);
        if (localhost)
            connector.setHost("127.0.0.1");
        _server.addConnector(connector);
        if (_server.isStarted())
            connector.start();
        else
            connector.open();

        return "http://"+(localhost?"127.0.0.1":
            InetAddress.getLocalHost().getHostAddress()
        )+":"+connector.getLocalPort();
    }
