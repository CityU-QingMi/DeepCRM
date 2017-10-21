    @Before
    public void prepare() throws Exception
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        _connector.setIdleTimeout(60000);
        _server.addConnector(_connector);
        _server.setHandler(new DumpHandler());
        _server.start();
    }
