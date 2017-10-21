    @BeforeClass
    public static void startServer() throws Exception
    {
        server = new Server();

        HttpConfiguration config = new HttpConfiguration();
        config.setSendServerVersion(false);

        HttpConnectionFactory httpConnectionFactory = new HttpConnectionFactory(config, HttpCompliance.RFC2616);
        httpConnectionFactory.setRecordHttpComplianceViolations(true);
        connector = new LocalConnector(server, null, null, null, -1, httpConnectionFactory);

        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.setWelcomeFiles(new String[]{"index.html", "index.jsp", "index.htm"});

        context.addServlet(DumpRequestHeadersServlet.class, "/dump/*");
        context.addFilter(ReportViolationsFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        server.setHandler(context);
        server.addConnector(connector);

        server.start();
    }
