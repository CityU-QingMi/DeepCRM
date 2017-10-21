    @Before
    public void init() throws Exception
    {
        _server = new Server();
        HttpConnectionFactory http = new HttpConnectionFactory();
        http.setInputBufferSize(1024);
        http.getHttpConfiguration().setRequestHeaderSize(512);
        http.getHttpConfiguration().setResponseHeaderSize(512);
        http.getHttpConfiguration().setOutputBufferSize(2048);
        http.getHttpConfiguration().addCustomizer(new ForwardedRequestCustomizer());
        _connector = new LocalConnector(_server,http);
        _server.addConnector(_connector);
        _connector.setIdleTimeout(500);
        _handler = new RequestHandler();
        _server.setHandler(_handler);
        
        ErrorHandler errors = new ErrorHandler();
        errors.setShowStacks(true);
        _server.addBean(errors);
        _server.start();
    }
