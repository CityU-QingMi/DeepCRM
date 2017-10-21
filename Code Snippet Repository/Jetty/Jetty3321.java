    @Before
    public void init() throws Exception
    {
        server = new Server();

        HttpConfiguration config = new HttpConfiguration();
        config.setRequestHeaderSize(1024);
        config.setResponseHeaderSize(1024);
        config.setSendDateHeader(true);
        HttpConnectionFactory http = new HttpConnectionFactory(config);

        connector = new LocalConnector(server,http,null);
        connector.setIdleTimeout(5000);
        server.addConnector(connector);
        server.setHandler(new DumpHandler());
        ErrorHandler eh=new ErrorHandler();
        eh.setServer(server);
        server.addBean(eh);
        server.start();
    }
