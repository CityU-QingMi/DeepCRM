    @Before
    public void init() throws Exception
    {
        _server = new Server();
        _connector = new LocalConnector(_server);
        _server.addConnector(_connector);

        GzipHandler gzipHandler = new GzipHandler();
        gzipHandler.setExcludedAgentPatterns();
        gzipHandler.setMinGzipSize(16);
        gzipHandler.setInflateBufferSize(4096);

        ServletContextHandler context = new ServletContextHandler(gzipHandler,"/ctx");
        ServletHandler servlets = context.getServletHandler();
        
        _server.setHandler(gzipHandler);
        gzipHandler.setHandler(context);
        servlets.addServletWithMapping(MicroServlet.class,"/micro");
        servlets.addServletWithMapping(MicroChunkedServlet.class,"/microchunked");
        servlets.addServletWithMapping(TestServlet.class,"/content");
        servlets.addServletWithMapping(ForwardServlet.class,"/forward");
        servlets.addServletWithMapping(IncludeServlet.class,"/include");
        servlets.addServletWithMapping(EchoServlet.class,"/echo/*");
        
        _server.start();
    }
