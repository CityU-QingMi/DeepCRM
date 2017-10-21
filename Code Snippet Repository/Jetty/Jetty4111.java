    @Before
    public void init() throws Exception
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        _connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendServerVersion(false);
        _connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendDateHeader(false);
        _server.addConnector( _connector );

        _contextCollection = new ContextHandlerCollection();
        _server.setHandler(_contextCollection);

        _context0 = new ServletContextHandler();
        _context0.setContextPath("/context path");
        _contextCollection.addHandler(_context0);
        _context0.addFilter(AsyncFilter.class,"/*",EnumSet.of(DispatcherType.REQUEST));
        _context0.addServlet(TestServlet.class,"/test servlet/*");
        _context0.addServlet(AsyncServlet.class,"/async servlet/*");
        
        _context1 = new ServletContextHandler();
        _context1.setContextPath("/redirecting context");
        _contextCollection.addHandler(_context1);

        _server.start();
    }
