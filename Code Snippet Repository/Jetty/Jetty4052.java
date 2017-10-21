    @Before
    public void init() throws Exception
    {
        server = new Server();

        connector = new LocalConnector(server); 
        connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendServerVersion(false);

        context = new ServletContextHandler();
        context.setContextPath("/context");
        context.setWelcomeFiles(new String[]{"index.html", "index.jsp", "index.htm"});

        server.setHandler(context);
        server.addConnector(connector);


        testdir.ensureEmpty();
        File resBase = testdir.getPathFile("docroot").toFile();
        FS.ensureDirExists(resBase);
        File data = new File(resBase, "data.txt");
        createFile(data, DATA);
        String resBasePath = resBase.getAbsolutePath();

        ServletHolder defholder = context.addServlet(DefaultServlet.class, "/");
        defholder.setInitParameter("acceptRanges", "true");
        defholder.setInitParameter("resourceBase", resBasePath);

        server.start();
    }
