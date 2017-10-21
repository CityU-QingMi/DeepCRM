    @Before
    public void setUp() throws Exception
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        _connector.setIdleTimeout(5000);
        _connector.getConnectionFactory(HttpConnectionFactory.class).getHttpConfiguration().setSendDateHeader(false);
        _server.addConnector(_connector);

        _contextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        _contextHandler.setContextPath("/ctx");
        _contextHandler.addServlet(new ServletHolder(new TestServlet()), "/servletPath");
        _contextHandler.addServlet(new ServletHolder(new TestServlet()), "/path with spaces/servletPath");
        _contextHandler.addServlet(new ServletHolder(new TestServlet2()), "/servletPath2");

        ServletHolder testHolder = new ServletHolder(new TestServlet());
        testHolder.setInitParameter("dispatchPath", "/test2/something%2felse");
        _contextHandler.addServlet(testHolder, "/test/*");
        _contextHandler.addServlet(new ServletHolder(new TestServlet2()), "/test2/*");

        _contextHandler.addServlet(new ServletHolder(new SelfDispatchingServlet()), "/self/*");

        _contextHandler.addServlet(new ServletHolder(new TestStartThrowServlet()), "/startthrow/*");
        _contextHandler.addServlet(new ServletHolder(new ForwardingServlet()), "/forward");
        _contextHandler.addServlet(new ServletHolder(new AsyncDispatchingServlet()), "/dispatchingServlet");
        _contextHandler.addServlet(new ServletHolder(new ExpireServlet()), "/expire/*");
        _contextHandler.addServlet(new ServletHolder(new BadExpireServlet()), "/badexpire/*");
        _contextHandler.addServlet(new ServletHolder(new ErrorServlet()), "/error/*");

        ErrorPageErrorHandler error_handler = new ErrorPageErrorHandler();
        _contextHandler.setErrorHandler(error_handler);
        error_handler.addErrorPage(500, "/error/500");
        error_handler.addErrorPage(IOException.class.getName(), "/error/IOE");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]
                {_contextHandler, new DefaultHandler()});

        _server.setHandler(handlers);
        _server.start();
    }
