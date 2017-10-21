    @Before
    public void init() throws Exception
    {
        _server = new Server();

        
        HttpConnectionFactory http = new HttpConnectionFactory();
        http.getHttpConfiguration().setRequestHeaderSize(1024);
        http.getHttpConfiguration().setResponseHeaderSize(1024);
        
        ProxyConnectionFactory proxy = new ProxyConnectionFactory();
        
        _connector = new LocalConnector(_server,null,null,null,1,proxy,http);
        _connector.setIdleTimeout(1000);
        _server.addConnector(_connector);
        _server.setHandler(new DumpHandler());
        ErrorHandler eh=new ErrorHandler();
        eh.setServer(_server);
        _server.addBean(eh);
        _server.start();
    }
