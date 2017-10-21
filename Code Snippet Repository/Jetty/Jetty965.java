    protected void start(HttpServlet servlet) throws Exception
    {
        HTTP2ServerConnectionFactory connectionFactory = new HTTP2ServerConnectionFactory(new HttpConfiguration());
        connectionFactory.setInitialSessionRecvWindow(FlowControlStrategy.DEFAULT_WINDOW_SIZE);
        connectionFactory.setInitialStreamRecvWindow(FlowControlStrategy.DEFAULT_WINDOW_SIZE);
        prepareServer(connectionFactory);
        ServletContextHandler context = new ServletContextHandler(server, "/", true, false);
        context.addServlet(new ServletHolder(servlet), servletPath + "/*");
        customizeContext(context);
        server.start();

        prepareClient();
        client.start();
    }
