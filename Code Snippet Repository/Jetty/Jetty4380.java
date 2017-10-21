    @Before
    public void startServer() throws Exception
    {
        server = new Server(0);
        connector = (NetworkConnector)server.getConnectors()[0];

        String contextPath = "/test";
        context = new ServletContextHandler(server, contextPath, ServletContextHandler.SESSIONS);
        server.start();
    }
