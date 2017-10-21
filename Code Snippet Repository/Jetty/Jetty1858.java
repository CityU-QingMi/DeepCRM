    @Test
    public void testNoRegistryHostNonDefaultRegistryPort() throws Exception
    {
        ServerSocket serverSocket = new ServerSocket(0);
        int registryPort = serverSocket.getLocalPort();
        serverSocket.close();
        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://:" + registryPort + "/jmxrmi"), objectName);
        connectorServer.start();

        // Verify that I can connect to the RMI registry using a non-loopback address.
        new Socket(InetAddress.getLocalHost(), registryPort).close();
        try
        {
            // Verify that I cannot connect to the RMI registry using the loopback address.
            new Socket(InetAddress.getLoopbackAddress(), registryPort).close();
            Assert.fail();
        }
        catch (ConnectException ignored)
        {
            // Ignored.
        }
    }
