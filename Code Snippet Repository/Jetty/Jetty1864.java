    @Test
    public void testRMIServerPort() throws Exception
    {
        ServerSocket server = new ServerSocket(0);
        int port = server.getLocalPort();
        server.close();

        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi://localhost:" + port + "/jndi/rmi:///jmxrmi"), objectName);
        connectorServer.start();

        JMXServiceURL address = connectorServer.getAddress();
        Assert.assertEquals(port, address.getPort());

        InetAddress loopback = InetAddress.getLoopbackAddress();
        new Socket(loopback, port).close();
    }
