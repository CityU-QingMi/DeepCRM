    @Test
    public void testRMIServerAndRMIRegistryOnSameHostAndSamePort() throws Exception
    {
        // RMI can multiplex connections on the same address and port for different
        // RMI objects, in this case the RMI registry and the RMI server. In this
        // case, the RMIServerSocketFactory will be invoked only once.
        // The case with different address and same port is already covered by TCP,
        // that can listen to 192.168.0.1:1099 and 127.0.0.1:1099 without problems.

        String host = "localhost";
        ServerSocket serverSocket = new ServerSocket(0);
        int port = serverSocket.getLocalPort();
        serverSocket.close();

        connectorServer = new ConnectorServer(new JMXServiceURL("rmi", host, port, "/jndi/rmi://" + host + ":" + port + "/jmxrmi"), objectName);
        connectorServer.start();

        JMXServiceURL address = connectorServer.getAddress();
        Assert.assertEquals(port, address.getPort());
    }
