    private void openLoopbackConnection() throws IOException
    {
        server = ManagementFactory.getPlatformMBeanServer();

        JMXServiceURL serviceUrl = new JMXServiceURL("service:jmx:rmi://");
        connectorServer = JMXConnectorServerFactory.newJMXConnectorServer(serviceUrl,null,server);
        connectorServer.start();

        this.serviceUrl = connectorServer.getAddress().toString();

        serverConnector = JMXConnectorFactory.connect(connectorServer.getAddress());
        serviceConnection = serverConnector.getMBeanServerConnection();
    }
