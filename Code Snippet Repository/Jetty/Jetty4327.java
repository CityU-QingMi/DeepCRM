    @Before
    public void setUp() throws Exception
    {
        testdir.ensureEmpty();

        File testFile = testdir.getPathFile("file.txt").toFile();
        try (OutputStream testOut = new BufferedOutputStream(new FileOutputStream(testFile)))
        {
            ByteArrayInputStream testIn = new ByteArrayInputStream(__content.getBytes("ISO8859_1"));
            IO.copy(testIn,testOut);
        }

        tester=new ServletTester("/context");
        tester.getContext().setResourceBase(testdir.getPath().toString());
        tester.getContext().addServlet(org.eclipse.jetty.servlet.DefaultServlet.class, "/");
        
        GzipHandler gzipHandler = new GzipHandler();
        tester.getContext().insertHandler(gzipHandler);
        tester.start();
    }
