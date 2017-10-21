    @Before
    public void setUp() throws Exception
    {
        _connector = new ServerConnector(_server);
        _server.setConnectors(new Connector[]{ _connector });

        _log=new ArrayList<>();
        RequestLog log=new Log();
        RequestLogHandler logHandler = new RequestLogHandler();
        logHandler.setRequestLog(log);
        _server.setHandler(logHandler);
        _expectedLogs=1;
        _expectedCode="200 ";

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/ctx");
        logHandler.setHandler(context);
        context.addEventListener(new DebugListener());
        
        _errorHandler = new ErrorPageErrorHandler();
        context.setErrorHandler(_errorHandler);
        _errorHandler.addErrorPage(300,599,"/error/custom");

        _servletHandler=context.getServletHandler();
        ServletHolder holder=new ServletHolder(_servlet);
        holder.setAsyncSupported(true);
        _servletHandler.addServletWithMapping(holder,"/error/*");
        _servletHandler.addServletWithMapping(holder,"/path/*");
        _servletHandler.addServletWithMapping(holder,"/path1/*");
        _servletHandler.addServletWithMapping(holder,"/path2/*");
        _servletHandler.addServletWithMapping(holder,"/p th3/*");
        _servletHandler.addServletWithMapping(new ServletHolder(new FwdServlet()),"/fwd/*");
        ServletHolder holder2=new ServletHolder("NoAsync",_servlet);
        holder2.setAsyncSupported(false);
        _servletHandler.addServletWithMapping(holder2,"/noasync/*");
        _server.start();
        _port=_connector.getLocalPort();
        __history.clear();
        __latch=new CountDownLatch(1);
    }
