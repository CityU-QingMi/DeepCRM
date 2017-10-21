    @Test
    public void testAnyRegistryHostBindsToAny() throws Exception
    {
        ServerSocket serverSocket = new ServerSocket(0);
        int registryPort = serverSocket.getLocalPort();
        serverSocket.close();
        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://0.0.0.0:" + registryPort + "/jmxrmi"), objectName);
        connectorServer.start();

        // Verify that I can connect to the RMI registry using a non-loopback address.
        new Socket(InetAddress.getLocalHost(), registryPort).close();
        // Verify that I can connect to the RMI registry using the loopback address.
        new Socket(InetAddress.getLoopbackAddress(), registryPort).close();
    }
