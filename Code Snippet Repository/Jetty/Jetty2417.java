    private Server createServer(ServletHolder servletHolder, String nodeName)
    {
        Server server = new Server();
        ServerConnector connector = new ServerConnector(server);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(server, CONTEXT_PATH, ServletContextHandler.SESSIONS);
        context.addServlet(servletHolder, SERVLET_PATH + "/*");

        if (nodeName != null)
        {
            DefaultSessionIdManager sessionIdManager = new DefaultSessionIdManager(server);
            sessionIdManager.setWorkerName(nodeName);
            server.setSessionIdManager(sessionIdManager);
        }

        return server;
    }
