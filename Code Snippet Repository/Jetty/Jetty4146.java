    @Before
    public void init() throws Exception
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        _connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendServerVersion(false);
        _connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendDateHeader(false);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        _server.addConnector(_connector);
        _server.setHandler(context);

        context.setContextPath("/");

        ServletHolder holder = context.addServlet(Invoker.class, "/servlet/*");
        holder.setInitParameter("nonContextServlets","true");
        _server.start();
    }
