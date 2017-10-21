    public void prepare(String path, HttpServlet servlet) throws Exception
    {
        _server = new Server();
        _connector = new ServerConnector(_server);
        _server.addConnector(_connector);

        ServletContextHandler context = new ServletContextHandler(_server, "/", false, false);
        context.addServlet(new ServletHolder(servlet), path);

        _server.start();
    }
