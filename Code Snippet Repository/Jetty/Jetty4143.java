    @Before
    public void startServer() throws Exception
    {
        this.server = new Server();
        ServerConnector connector = new ServerConnector(server);
        connector.setHost("localhost");
        connector.setPort(0);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(TopServlet.class, "/top");
        context.addServlet(IncludedServlet.class, "/included");

        server.setHandler(context);

        server.start();

        int port = connector.getLocalPort();
        String host = connector.getHost();

        baseUri = URI.create("http://" + host + ":" + port + "/");
    }
