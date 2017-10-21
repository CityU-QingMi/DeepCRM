    @Before
    public void setUp () throws Exception
    {
        JspFactory.setDefaultFactory(new JspFactoryImpl());
        _dir = MavenTestingUtils.getTestResourceDir("base");
        _tester = new ServletTester("/context");
        _tester.getContext().setClassLoader(new URLClassLoader(new URL[0], Thread.currentThread().getContextClassLoader()));
        ServletHolder jspHolder = _tester.getContext().addServlet(JettyJspServlet.class, "/*");
        jspHolder.setInitParameter("scratchdir", MavenTestingUtils.getTargetTestingDir().getAbsolutePath());
        _tester.getContext().setResourceBase(_dir.getAbsolutePath());
        _tester.getContext().setAttribute(InstanceManager.class.getName(), new SimpleInstanceManager());
        ServletHolder dfltHolder = new ServletHolder();
        dfltHolder.setName("default");
        dfltHolder.setHeldClass( DfltServlet.class);
        _tester.getContext().addServlet(dfltHolder, "/");

        _tester.start();
    }
