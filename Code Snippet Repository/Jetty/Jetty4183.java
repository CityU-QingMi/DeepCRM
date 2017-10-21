    @Before
    public void init() throws Exception
    {
        server = new Server();

        connector = new LocalConnector(server);
        connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendServerVersion(false);

        Path resBase = MavenTestingUtils.getTestResourcePathDir("contextResources");

        context = new ServletContextHandler();
        context.setContextPath("/context");
        context.setResourceBase(resBase.toFile().toURI().toASCIIString());

        server.setHandler(context);
        server.addConnector(connector);

        server.start();
    }
