    @Test
    public void testNoRegistryHostBindsToHost() throws Exception
    {
        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi:///jmxrmi"), objectName);
        connectorServer.start();

        // Verify that I can connect to the RMI registry using a non-loopback address.
        new Socket(InetAddress.getLocalHost(), 1099).close();
        try
        {
            // Verify that I cannot connect to the RMI registry using the loopback address.
            new Socket(InetAddress.getLoopbackAddress(), 1099).close();
            Assert.fail();
        }
        catch (ConnectException ignored)
        {
            // Ignored.
        }
    }
