    public void prepare(HttpServlet servlet) throws Exception
    {
        server = new Server();

        connector = new ServerConnector(server, sslContextFactory);
        server.addConnector(connector);

        contextPath = "/context";
        ServletContextHandler context = new ServletContextHandler(server, contextPath, true, false);
        servletPath = "/servlet";
        context.addServlet(new ServletHolder(servlet), servletPath);

        server.start();
    }
