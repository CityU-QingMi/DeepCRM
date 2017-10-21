    @Before
    public void init() throws Exception
    {
        _server = new Server();

        HttpConnectionFactory http = new HttpConnectionFactory();
        http.getHttpConfiguration().setRequestHeaderSize(1024);
        http.getHttpConfiguration().setResponseHeaderSize(1024);
        http.getHttpConfiguration().setOutputBufferSize(4096);
        
        _connector = new LocalConnector(_server,http,null);
        _server.addConnector(_connector);
        _swap=new HotSwapHandler();
        _handler=new ContentHandler();
        _swap.setHandler(_handler);
        _server.setHandler(_swap);
        _server.start();
    }
