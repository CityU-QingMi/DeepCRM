    @Before
    public void setUp()
        throws Exception
    {
        File docRoot = new File("target/test-output/docroot/");
        docRoot.mkdirs();
        docRoot.deleteOnExit();

        System.setProperty("org.eclipse.jetty.util.log.DEBUG","");
        _server = new Server();

        ServerConnector connector = new ServerConnector(_server);
        connector.setPort(0);
        _server.setConnectors(new Connector[] {connector});
        
        _handler = new TestHandler();
        _server.setHandler(_handler);

        MBeanContainer.resetUnique();
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        _mBeanContainer = new MBeanContainer(mBeanServer);
        _server.addBean(_mBeanContainer,true);
        _server.addBean(Log.getLog());
        
        _counter = _handler.getRequestCounter();
        _server.addBean(_counter);

        _server.start();

        startClient();

        _monitor = new JMXMonitor();

        int port = connector.getLocalPort();
        _requestUrl = "http://localhost:"+port+ "/";
    }
