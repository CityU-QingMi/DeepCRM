    protected void startServer(ServerConnector connector, Handler handler) throws Exception
    {
        _connector = connector;
        _httpConfiguration=_connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration();
        _httpConfiguration.setBlockingTimeout(-1);
        _httpConfiguration.setSendDateHeader(false);
        _server.addConnector(_connector);
        _server.setHandler(handler);
        _server.start();
        _serverURI = _server.getURI();
    }
