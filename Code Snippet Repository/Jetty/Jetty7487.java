    @Before
    public void connectToMBeanServer() throws Exception
    {
        startJetty();

        new CountDownLatch(1).await();

        _jmxc = JMXConnectorFactory.connect(_jmxURL);
        _mbsc = _jmxc.getMBeanServerConnection();
    }
