    @Before
    public void before()
        throws Exception
    {
        _server = new Server();
        _connector = new ServerConnector(_server);
        _local = new LocalConnector(_server);
        _server.setConnectors(new Connector[] { _local,_connector });

    }
