    @Before
    public void init() throws Exception
    {
        testdir.ensureEmpty();
        docRoot = testdir.getPathFile("docroot").toAbsolutePath().toFile();
        
        server = new Server();

        connector = new LocalConnector(server);
        connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendServerVersion(false);

        context = new ServletContextHandler();
        context.setBaseResource(Resource.newResource(docRoot));
        context.setContextPath("/context");
        context.setWelcomeFiles(new String[]{"index.html", "index.jsp", "index.htm"});

        server.setHandler(context);
        server.addConnector(connector);

        server.start();
    }
