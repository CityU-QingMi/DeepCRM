    @Before
    public void setUp() throws Exception 
    {
        _connector.setIdleTimeout(30000);
        _server.setConnectors(new Connector[] { _connector });

        _contextHandler.setContextPath("/");
        _contextHandler.addServlet(new ServletHolder(new TestServlet()), "/initialCall");
        _contextHandler.addServlet(new ServletHolder(new TestServlet()), "/firstDispatchWithNewQueryString");
        _contextHandler.addServlet(new ServletHolder(new TestServlet()), "/secondDispatchNewValueForExistingQueryString");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { _contextHandler, new DefaultHandler() });

        _server.setHandler(handlers);
        _server.start();
    }
