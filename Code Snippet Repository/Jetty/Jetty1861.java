    @Test
    public void testNoRMIHostBindsToHost() throws Exception
    {
        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi:///jmxrmi"), objectName);
        connectorServer.start();

        // Verify that I can connect to the RMI server using a non-loopback address.
        new Socket(InetAddress.getLocalHost(), connectorServer.getAddress().getPort()).close();
        try
        {
            // Verify that I cannot connect to the RMI server using the loopback address.
            new Socket(InetAddress.getLoopbackAddress(), connectorServer.getAddress().getPort()).close();
            Assert.fail();
        }
        catch (ConnectException ignored)
        {
            // Ignored.
        }
    }
