    @BeforeClass
    public static void setUp() throws Exception
    {
        _server = new Server();
        _config = new HttpConfiguration();
        _config.setOutputBufferSize(1024);
        _config.setOutputAggregationSize(256);
        _local = new LocalConnector(_server,new HttpConnectionFactory(_config));
        _server.addConnector(_local);

        _bufferedHandler = new BufferedResponseHandler();
        _bufferedHandler.getPathIncludeExclude().include("/include/*");
        _bufferedHandler.getPathIncludeExclude().exclude("*.exclude");
        _bufferedHandler.getMimeIncludeExclude().exclude("text/excluded");
        _bufferedHandler.setHandler(_test=new TestHandler());
        
        _contextHandler = new ContextHandler("/ctx");
        _contextHandler.setHandler(_bufferedHandler);

        _server.setHandler(_contextHandler);
        _server.start();
        
        // BufferedResponseHandler.LOG.setDebugEnabled(true);
    }
