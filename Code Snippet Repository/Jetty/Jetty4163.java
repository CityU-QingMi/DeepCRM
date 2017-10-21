    @BeforeClass
    public static void startServer() throws Exception
    {
        server = new Server();
        connector = new LocalConnector(server);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new SimulateUpgradeServlet()),"/ws/*");
        context.addServlet(new ServletHolder(new MultilineResponseValueServlet()),"/multiline/*");

        server.start();
    }
