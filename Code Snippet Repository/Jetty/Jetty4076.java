    public void prepare() throws Exception
    {
        server = new Server();
        connector = new LocalConnector(server);
        server.addConnector(connector);

        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(new ServletHolder(servlet1), "/one");
        context.addServlet(new ServletHolder(servlet2), "/two");

        server.start();
    }
