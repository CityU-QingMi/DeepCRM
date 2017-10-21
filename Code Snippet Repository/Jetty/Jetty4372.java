    @Before
    public void init() throws Exception
    {
        server = new Server();

        connector = new LocalConnector(server);
        connector.getConnectionFactory(HttpConfiguration.ConnectionFactory.class).getHttpConfiguration().setSendServerVersion(false);

        context = new ServletContextHandler();
 
        context.setContextPath("/context");
        context.setWelcomeFiles(new String[]{"index.html", "index.jsp", "index.htm"});
        
        File baseResourceDir = testdir.getEmptyPathDir().toFile();
        // Use resolved real path for Windows and OSX
        Path baseResourcePath = baseResourceDir.toPath().toRealPath();
        
        context.setBaseResource(Resource.newResource(baseResourcePath.toFile()));
        
        ServletHolder holder =context.addServlet(DataRateLimitedServlet.class,"/stream/*");
        holder.setInitParameter("buffersize",""+BUFFER);
        holder.setInitParameter("pause",""+PAUSE);
        server.setHandler(context);
        server.addConnector(connector);

        server.start();
    }
