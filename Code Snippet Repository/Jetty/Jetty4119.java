    @Before
    public void init() throws Exception
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SECURITY|ServletContextHandler.NO_SESSIONS);

        _server.addConnector(_connector);
        _server.setHandler(context);

        context.setContextPath("/");

        context.addServlet(DefaultServlet.class, "/");
        context.addServlet(FailServlet.class, "/fail/*");
        context.addServlet(FailClosedServlet.class, "/fail-closed/*");
        context.addServlet(ErrorServlet.class, "/error/*");
        
        ErrorPageErrorHandler error = new ErrorPageErrorHandler();
        context.setErrorHandler(error);
        error.addErrorPage(599,"/error/599");
        error.addErrorPage(IllegalStateException.class.getCanonicalName(),"/error/TestException");
        error.addErrorPage(ErrorPageErrorHandler.GLOBAL_ERROR_PAGE,"/error/GlobalErrorPage");
        
        _server.start();
        _stackless=new StacklessLogging(ServletHandler.class);
    }
