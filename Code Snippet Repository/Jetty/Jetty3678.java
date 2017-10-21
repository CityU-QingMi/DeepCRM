    @BeforeClass
    public static void init() throws Exception
    {
        _threads = new QueuedThreadPool();
        _threads.setMaxThreads(200);

        _server = new Server(_threads);
        _server.manage(_threads);
        _connector = new ServerConnector(_server,null,null,null,1, 1,new HttpConnectionFactory());
        _connector.setAcceptQueueSize(5000);
        _connector.setIdleTimeout(30000);
        _server.addConnector(_connector);

        TestHandler _handler = new TestHandler();
        _server.setHandler(_handler);

        _server.start();
    }
