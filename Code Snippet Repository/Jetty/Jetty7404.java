    @BeforeClass
    public static void startServer() throws Exception
    {
        server = new Server(0);

        // Configure LoginService
        HashLoginService login = new HashLoginService();
        login.setName("Test Realm");
        File realmFile = MavenTestingUtils.getTestResourceFile("realm.properties");
        login.setConfig(realmFile.getAbsolutePath());
        server.addBean(login);

        // Configure WebApp
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        File webappBase = MavenTestingUtils.getTestResourceDir("docroots/jsp");
        context.setResourceBase(webappBase.getAbsolutePath());
        context.setClassLoader(Thread.currentThread().getContextClassLoader());

        // add default servlet
        ServletHolder defaultServHolder = context.addServlet(DefaultServlet.class,"/");
        defaultServHolder.setInitParameter("aliases","true"); // important! must be TRUE

        // add jsp
        ServletHolder jsp = new ServletHolder(new FakeJspServlet());
        context.addServlet(jsp,"*.jsp");
        jsp.setInitParameter("classpath",context.getClassPath());

        // add context
        server.setHandler(context);

        server.start();

        int port = ((NetworkConnector)server.getConnectors()[0]).getLocalPort();

        serverURI = new URI("http://localhost:" + port + "/");
    }
