    @Test
    public void testLocalhostRegistryBindsToLoopback() throws Exception
    {
        connectorServer = new ConnectorServer(new JMXServiceURL("service:jmx:rmi:///jndi/rmi://localhost:1099/jmxrmi"), objectName);
        connectorServer.start();

        InetAddress localHost = InetAddress.getLocalHost();
        if (!localHost.isLoopbackAddress())
        {
            try
            {
                // Verify that I cannot connect to the RMIRegistry using a non-loopback address.
                new Socket(localHost, 1099);
                Assert.fail();
            }
            catch (ConnectException ignored)
            {
                // Ignored.
            }
        }

        InetAddress loopback = InetAddress.getLoopbackAddress();
        new Socket(loopback, 1099).close();
    }
