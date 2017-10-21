    @Test
    public void testAnyRMIHostBindsToAny() throws Exception
    {
        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi://0.0.0.0/jndi/rmi:///jmxrmi"), objectName);
        connectorServer.start();

        // Verify that I can connect to the RMI server using a non-loopback address.
        new Socket(InetAddress.getLocalHost(), connectorServer.getAddress().getPort()).close();
        // Verify that I can connect to the RMI server using the loopback address.
        new Socket(InetAddress.getLoopbackAddress(), connectorServer.getAddress().getPort()).close();
    }
