    private void prepareServer(HttpServlet servlet) throws Exception
    {
        QueuedThreadPool executor = new QueuedThreadPool();
        executor.setName("server");
        server = new Server(executor);
        serverConnector = new ServerConnector(server);
        server.addConnector(serverConnector);

        ServletContextHandler appCtx = new ServletContextHandler(server, "/", true, false);
        ServletHolder appServletHolder = new ServletHolder(servlet);
        appCtx.addServlet(appServletHolder, "/*");

        server.start();
    }
