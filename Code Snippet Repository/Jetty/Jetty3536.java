    @Before
    public void init() throws Exception
    {
        _server = new Server();
        _connector = initConnector();
        _server.addConnector(_connector);

        SessionHandler session = new SessionHandler();
        _handler = new SuspendHandler();
        session.setHandler(_handler);

        _server.setHandler(session);
        _server.start();

        reset();
    }
