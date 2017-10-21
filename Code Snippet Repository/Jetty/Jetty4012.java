    protected void prepare(HttpServlet servlet) throws Exception
    {
        server = new Server();
        connector = new ServerConnector(server);
        server.addConnector(connector);
        String contextPath = "/context";
        context = new ServletContextHandler(server, contextPath, ServletContextHandler.NO_SESSIONS);
        ServletHolder servletHolder = new ServletHolder(servlet);
        String servletPath = "/path";
        context.addServlet(servletHolder, servletPath);
        uri = contextPath + servletPath;
        server.start();
    }
