    private void openLoopbackConnection()
        throws IOException
    {
        _server = ManagementFactory.getPlatformMBeanServer();       

        JMXServiceURL serviceUrl = new JMXServiceURL("service:jmx:rmi://");
        _connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(serviceUrl, null, _server);
        _connectorServer.start();
        
        _serviceUrl = _connectorServer.getAddress().toString();
        
        _serverConnector = JMXConnectorFactory.connect(_connectorServer.getAddress());      
        _serviceConnection = _serverConnector.getMBeanServerConnection();
    }
