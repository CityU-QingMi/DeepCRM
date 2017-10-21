    @Before
    public void init() throws Exception
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        _connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendServerVersion(false);
        _connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendDateHeader(false);

        _contextCollection = new ContextHandlerCollection();
        _contextHandler = new ServletContextHandler();
        _contextHandler.setContextPath("/context");
        _contextCollection.addHandler(_contextHandler);
        _resourceHandler = new ResourceHandler();
        _resourceHandler.setResourceBase(MavenTestingUtils.getTestResourceDir("dispatchResourceTest").getAbsolutePath());
        _resourceHandler.setPathInfoOnly(true);
        ContextHandler resourceContextHandler = new ContextHandler("/resource");
        resourceContextHandler.setHandler(_resourceHandler);
        _contextCollection.addHandler(resourceContextHandler);
        _server.setHandler(_contextCollection);
        _server.addConnector( _connector );

        _server.start();
    }
