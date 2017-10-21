    @Before
    public void setUp() throws Exception
    {
        _dir = File.createTempFile("testmultupart",null);
        assertTrue(_dir.delete());
        assertTrue(_dir.mkdir());
        _dir.deleteOnExit();
        assertTrue(_dir.isDirectory());

        tester=new ServletTester("/context");
        tester.getContext().setResourceBase(_dir.getCanonicalPath());
        tester.getContext().addServlet(TestServlet.class, "/");
        tester.getContext().setAttribute("javax.servlet.context.tempdir", _dir);
        multipartFilter = tester.getContext().addFilter(MultiPartFilter.class,"/*", EnumSet.of(DispatcherType.REQUEST));
        multipartFilter.setInitParameter("deleteFiles", "true");
        multipartFilter.setInitParameter("fileOutputBuffer", "1"); //write a file if  there's more than 1 byte content
        tester.start();
    }
