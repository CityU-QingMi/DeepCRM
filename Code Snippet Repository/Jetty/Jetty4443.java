    @Before
    public void setUp() throws Exception
    {
        _tester = new ServletTester();
        _tester.setContextPath("/context");
        _tester.addServlet(TestServlet.class, "/test");
        TestServlet.__maxSleepers = 0;
        TestServlet.__sleepers = 0;

        _connectors = new LocalConnector[NUM_CONNECTIONS];
        for (int i = 0; i < _connectors.length; ++i)
            _connectors[i] = _tester.createLocalConnector();

        _tester.start();
    }
