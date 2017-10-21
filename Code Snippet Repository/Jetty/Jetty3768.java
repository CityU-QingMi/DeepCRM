    @Before
    public void before() throws Exception
    {
        _log = new Exchanger<String>();
        _server = new Server();
        _connector = new LocalConnector(_server);
        _server.addConnector(_connector);
        _server.setRequestLog(new Log());
        _server.setHandler(new TestHandler());
        _server.start();
    }
