    @Before
    public void setUp() throws Exception
    {
        _dir = File.createTempFile("testPutFilter",null);
        assertTrue(_dir.delete());
        assertTrue(_dir.mkdir());
        _dir.deleteOnExit();
        assertTrue(_dir.isDirectory());

        tester=new ServletTester("/context");
        tester.setResourceBase(_dir.getCanonicalPath());
        tester.addServlet(org.eclipse.jetty.servlet.DefaultServlet.class, "/");
        FilterHolder holder = tester.addFilter(PutFilter.class,"/*",EnumSet.of(DispatcherType.REQUEST));
        holder.setInitParameter("delAllowed","true");
        // Bloody Windows does not allow file renaming
        if (!System.getProperty("os.name").toLowerCase(Locale.ENGLISH).contains("windows"))
            holder.setInitParameter("putAtomic","true");
        tester.start();
    }
