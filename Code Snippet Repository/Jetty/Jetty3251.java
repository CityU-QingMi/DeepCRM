    @Before
    public void init() throws Exception
    {
        _server.manage(_threads);
        _threads.setMaxThreads(50);
        _connector = new ServerConnector(_server);
        _connector.setIdleTimeout(120000);
        _server.setConnectors(new Connector[]{ _connector });
        _server.setHandler(_handler);
        _server.start();
        _port=_connector.getLocalPort();
        _addr=InetAddress.getLocalHost();
    }
